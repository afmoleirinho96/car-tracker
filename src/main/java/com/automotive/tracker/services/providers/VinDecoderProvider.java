package com.automotive.tracker.services.providers;

import com.automotive.tracker.model.VinDecoderOperationId;
import com.automotive.tracker.model.annotations.VinDecoderApi;
import com.automotive.tracker.to.providers.VinDecode;
import com.automotive.tracker.to.providers.VinDecodeInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VinDecoderProvider {

    private final RestTemplate restTemplate;
    private final String vinDecodeApiKey;
    private final String vinDecodeSecretKey;
    private final String vinDecodeBaseUrl;

    public VinDecoderProvider(
        @VinDecoderApi RestTemplate restTemplate,
        @Value("${automotive.vehicleTracker.services.providers.vinDecoderProvider.secretKey}") String secretKey,
        @Value("${automotive.vehicleTracker.services.providers.vinDecoderProvider.apiKey}") String apiKey,
        @Value("${automotive.vehicleTracker.services.providers.vinDecoderProvider.baseUrl}") String baseUrl
    ) {
        this.vinDecodeApiKey = apiKey;
        this.vinDecodeSecretKey = secretKey;
        this.vinDecodeBaseUrl = baseUrl;
        this.restTemplate = restTemplate;
    }

    public VinDecodeInfo vinDecodeInfo(String decodeInfoUrl, String vin) {
        String vinDecodeInfoUrl = buildUrl(VinDecoderOperationId.INFO, decodeInfoUrl, vin);

        return restTemplate.getForObject(
            vinDecodeInfoUrl,
            VinDecodeInfo.class,
            vin
        );
    }

    public VinDecode vinDecode(String decodeUrl, String vin) {
        String vinDecodeUrl = buildUrl(VinDecoderOperationId.DECODE, decodeUrl, vin);

        return restTemplate.getForObject(
            vinDecodeUrl,
            VinDecode.class,
            vin
        );
    }

    private String buildUrl(VinDecoderOperationId operationId, String operationUri, String vin) {
        String controlsum = generateControlSum(vin, operationId.label);
        return vinDecodeBaseUrl + "/" + vinDecodeApiKey + "/" + controlsum + operationUri + vin + ".json";
    }

    private String generateControlSum(String vin, String operationId) {
        return DigestUtils.sha1Hex(vin + "|" + operationId + "|" + vinDecodeApiKey + "|" + vinDecodeSecretKey).substring(0, 10);
    }
}



