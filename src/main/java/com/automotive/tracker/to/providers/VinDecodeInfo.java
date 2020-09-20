package com.automotive.tracker.to.providers;

import lombok.Data;

@Data
public class VinDecodeInfo {

    private String price;
    private String price_currency;
    private String[] decode;
}
