package com.my.insurance.entity;

import jakarta.persistence.*;

@Entity
public class TravelArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code; // AREA1
    private String name; // Area 1
    private String description;

    private Boolean supportsAnnual;

    public Boolean getSupportsAnnual() {
        return supportsAnnual;
    }

    public void setSupportsAnnual(Boolean supportsAnnual) {
        this.supportsAnnual = supportsAnnual;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}