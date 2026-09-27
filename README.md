# Car Tracker

A Spring Boot REST API that keeps a small registry of vehicles and decodes their **VINs** (Vehicle
Identification Numbers) into a full vehicle specification - make, model, model year, body, engine,
dimensions and more - through the [vindecoder.eu VIN Decoder API](https://vindecoder.eu/api/) by
[Vincario](https://vincario.com).

- **Vehicles:** create, list, read, re-VIN and delete vehicles over REST.
- **VIN info:** which data points the provider has for a vehicle's VIN.
- **VIN decode:** the full decode (about 50 label/value pairs) plus the API credits left on your account.
- **Signed requests:** every provider call carries a SHA-1 control sum derived from the VIN, the
  operation and your keys.
- **API docs:** Swagger UI and OpenAPI specs out of the box.

## Tech stack

Java 8 · Spring Boot 2.3 (Web, HATEOAS, DevTools) · MapStruct 1.4 · Lombok · Springfox 3 (Swagger) ·
Apache Commons Codec · Gradle 6.6.1 (wrapper) · SonarQube plugin

## Requirements

- **JDK 8 to 14.** The Gradle 6.6.1 wrapper doesn't run on newer JDKs; JDK 11 is a safe choice.
- **A vindecoder.eu API key and secret key.** Request a free trial at [vincario.com](https://vincario.com).
  They're only used by the two VIN endpoints, but the app needs all three settings below to start.

## Configuration

The VIN provider is configured through three Spring properties, which Spring Boot also reads from
environment variables. They're listed in [`.env.example`](.env.example):

| Environment variable | Spring property | Value |
| --- | --- | --- |
| `AUTOMOTIVE_VEHICLETRACKER_SERVICES_PROVIDERS_VINDECODERPROVIDER_APIKEY` | `automotive.vehicleTracker.services.providers.vinDecoderProvider.apiKey` | Your vindecoder.eu API key |
| `AUTOMOTIVE_VEHICLETRACKER_SERVICES_PROVIDERS_VINDECODERPROVIDER_SECRETKEY` | `automotive.vehicleTracker.services.providers.vinDecoderProvider.secretKey` | Your vindecoder.eu secret key |
| `AUTOMOTIVE_VEHICLETRACKER_SERVICES_PROVIDERS_VINDECODERPROVIDER_BASEURL` | `automotive.vehicleTracker.services.providers.vinDecoderProvider.baseUrl` | `https://api.vindecoder.eu/3.2` |

Spring Boot 2.3 doesn't load `.env` files by itself, so export them into your shell first:

```bash
cp .env.example .env        # then fill in your keys - .env is git-ignored
set -a; source .env; set +a
```

In IntelliJ IDEA, add the same variables to the run configuration instead.

The seed data lives in `src/main/resources/vehicles/vehicles.json`, set by
`automotive.vehicleTracker.services.vehicleConfigFile` in `application.properties`.

## Run

```bash
./gradlew bootRun
```

The API starts on <http://localhost:8080> with three seed vehicles. On Windows, use Git Bash or WSL:
the repository only ships the Unix `gradlew` script.

- Swagger UI: <http://localhost:8080/swagger-ui/>
- OpenAPI: <http://localhost:8080/v3/api-docs> (Swagger 2: `/v2/api-docs`)

To build and run a standalone jar:

```bash
./gradlew build
java -jar build/libs/tracker-0.0.1-SNAPSHOT.jar
```

## API

| Method | Path | Body | Response |
| --- | --- | --- | --- |
| `GET` | `/vehicles` | - | `200` all vehicles |
| `GET` | `/vehicles/{id}` | - | `200` the vehicle, `404` if unknown |
| `POST` | `/vehicles` | JSON: `vin`, `owner`, `plateDate` | `200` the vehicle with its generated `id` |
| `PATCH` | `/vehicles/{id}` | the new VIN, as `text/plain` | `204`, `404` if unknown |
| `DELETE` | `/vehicles/{id}` | - | `204`, `404` if unknown |
| `GET` | `/vehicles/{id}/vin-info` | - | `200` the data points available for its VIN |
| `GET` | `/vehicles/{id}/vin-info-decoded` | - | `200` the decoded VIN and the credits left |

```bash
# Register a vehicle (ids are generated UUIDs)
curl -X POST localhost:8080/vehicles \
  -H 'Content-Type: application/json' \
  -d '{"vin":"WBA3A5C51CF256985","owner":"Jane Doe","plateDate":"03/2019"}'

# Change its VIN - the body is the raw VIN
curl -X PATCH localhost:8080/vehicles/{id} -H 'Content-Type: text/plain' --data 'WBA3A5C51CF256985'

# Decode it (spends vindecoder.eu credits)
curl localhost:8080/vehicles/{id}/vin-info-decoded
```

`vin-info-decoded` responds with the provider's label/value pairs:

```json
{
  "apiRequestsLeft": 19,
  "vinDecode": [
    { "label": "Make", "value": "..." },
    { "label": "Model", "value": "..." },
    { "label": "Model Year", "value": "..." }
  ]
}
```

## How the VIN decode works

1. The vehicle is looked up by id to get its VIN (`404` when the vehicle is unknown).
2. A **control sum** signs the request: the first 10 characters of
   `sha1("{vin}|{operation}|{apiKey}|{secretKey}")`, where the operation is `info` or `decode`.
   The secret key never appears in the URL.
3. The provider is called with a 3-second connect and read timeout:
   - `{baseUrl}/{apiKey}/{controlSum}/decode/info/{vin}.json` for `vin-info`
   - `{baseUrl}/{apiKey}/{controlSum}/decode/{vin}.json` for `vin-info-decoded`
4. MapStruct maps the provider's response to the API's DTOs. `apiRequestsLeft` comes from the
   provider's account balance (`"API Decode"`).

## Project structure

```text
src/main/java/com/automotive/tracker
├── configuration/   RestTemplate for the VIN provider (timeouts, qualifier) and Swagger
├── rest/            VehicleResource, VinDecoderResource
├── services/        VehicleService, VinDecoderService, the seed loader, and providers/VinDecoderProvider
├── repository/      a generic CrudRepository and its in-memory implementation
├── mapper/          MapStruct mappers between provider responses, models and DTOs
├── model/           Vehicle, the provider operation ids, and the @Json / @VinDecoderApi annotations
├── to/              transfer objects: providers/ (vindecoder.eu responses) and rest/ (API responses)
└── exceptions/      404s for unknown vehicles and failed decodes
src/main/resources
├── application.properties
└── vehicles/vehicles.json    the seed vehicles
```

## Notes

- **In-memory storage.** Vehicles live in a `ConcurrentHashMap`: they reset on every restart and get
  new ids each time.
- **Placeholder seed VINs.** The seed vehicles use VINs like `123`. Give a vehicle a real 17-character
  VIN (`PATCH`) or create one (`POST`) before calling the VIN endpoints.
- **Provider errors.** When the provider rejects a request (wrong keys, unknown VIN, no credits left),
  the API currently answers with a `500`.
