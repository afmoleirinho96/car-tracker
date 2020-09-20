package com.automotive.tracker.to.providers;

import lombok.Data;

@Data
@SuppressWarnings("java:S116")
public class VinDecodeInfo {

    private String price;
    private String price_currency;
    private String[] decode;
}
