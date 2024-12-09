package com.alten.back.product;

public enum Status {
    INSTOCK("INSTOCK") , LOWSTOCK("LOWSTOCK") , OUTOFSTOCK("OUTOFSTOCK");

    private String value;

    Status(String type) {
        this.value = type;
    }

    public String value() {
        return this.value;
    }
}
