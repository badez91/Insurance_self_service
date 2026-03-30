package com.my.insurance.dto;

public class InsuranceResponse {

    private Long policyId;
    private Double totalPrice;
    private String message;

    public InsuranceResponse(Long policyId, Double totalPrice, String message) {
        this.policyId = policyId;
        this.totalPrice = totalPrice;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Long getPolicyId() {
        return policyId;
    }
}