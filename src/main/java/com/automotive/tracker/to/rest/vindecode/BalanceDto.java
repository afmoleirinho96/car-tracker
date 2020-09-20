package com.automotive.tracker.to.rest.vindecode;

import com.automotive.tracker.model.annotations.Json;
import lombok.Data;

@Json
@Data
public class BalanceDto {
    private Integer apiRequestsLeft;
}
