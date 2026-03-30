package com.my.insurance.util;

import java.util.Map;

public enum PricingEnum {

    SINGLE(Map.of(
            "PLAN_A_AREA1", 10.0,
            "PLAN_A_AREA2", 15.0,
            "PLAN_A_AREA3", 20.0,
            "PLAN_A_AREA4", 5.0,
            "PLAN_B_AREA1", 20.0,
            "PLAN_B_AREA2", 30.0,
            "PLAN_B_AREA3", 40.0,
            "PLAN_B_AREA4", 10.0
    )),

    ANNUAL(Map.of(
            "PLAN_A_AREA1", 100.0,
            "PLAN_A_AREA2", 150.0,
            "PLAN_A_AREA3", 200.0,
            "PLAN_B_AREA1", 150.0,
            "PLAN_B_AREA2", 200.0,
            "PLAN_B_AREA3", 250.0
    ));

    private final Map<String, Double> pricing;

    PricingEnum(Map<String, Double> pricing) {
        this.pricing = pricing;
    }

    public Double get(String key) {
        return pricing.getOrDefault(key, 0.0);
    }
}