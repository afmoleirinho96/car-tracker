package com.automotive.tracker.model;

public enum VinDecoderOperationId {

    INFO("info"),
    DECODE("decode"),
    STOLEN_CHECK("stolen-check");

    public final String label;

    VinDecoderOperationId(String label) {
        this.label = label;
    }
}
