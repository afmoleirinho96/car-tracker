package com.automotive.tracker.to.providers;

import com.automotive.tracker.model.annotations.Json;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@Json
@SuppressWarnings("java:S116")
public class VinDecode {

    private String price;
    private String price_currency;
    private Balance balance;
    @JsonProperty("decode")
    private VinInfoDecode[] decode;

}
