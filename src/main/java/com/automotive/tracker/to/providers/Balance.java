package com.automotive.tracker.to.providers;

import com.automotive.tracker.model.annotations.Json;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@Json
public class Balance {
    @JsonProperty("API Decode")
    private Integer apiRequestsLeft;
}
