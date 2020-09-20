package com.automotive.tracker.to.providers;

import com.automotive.tracker.model.annotations.Json;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Json
@Data
public class VinInfoDecode {
   private String label;
   private String value;
}
