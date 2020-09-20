package com.automotive.tracker.to.rest.vindecode;

import com.automotive.tracker.model.annotations.Json;
import lombok.Data;

@Data
@Json
public class VinDecodeDto {

    private Integer apiRequestsLeft;
    private VinInfoDecodeDto[] vinDecode;
}
