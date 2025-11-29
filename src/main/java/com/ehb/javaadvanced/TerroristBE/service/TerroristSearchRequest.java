package com.ehb.javaadvanced.TerroristBE.service;

public class TerroristSearchRequest {
    private String value;
    private TerroristSearchType type;

    public TerroristSearchRequest(TerroristSearchType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TerroristSearchType  getType() {
        return type;
    }

    public void setType(TerroristSearchType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type.name() + " - " + this.value;
    }
}
