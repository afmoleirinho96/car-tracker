package com.automotive.tracker.to.providers;

import com.automotive.tracker.model.annotations.Json;
import lombok.Data;

@Json
@Data
public class VinInfoDecode {
    private String label;
    private String value;
}
