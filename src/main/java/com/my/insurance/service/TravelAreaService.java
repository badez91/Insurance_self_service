package com.my.insurance.service;

import com.my.insurance.entity.TravelArea;
import com.my.insurance.repository.TravelAreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelAreaService {

    private final TravelAreaRepository repo;

    public TravelAreaService(TravelAreaRepository repo) {
        this.repo = repo;
    }

    public List<TravelArea> getAll() {
        return repo.findAll();
    }

    public List<TravelArea> getAnnualAllowed() {
        return repo.findBySupportsAnnualTrue();
    }

    public TravelArea getByCode(String code) {
        return repo.findAll()
                .stream()
                .filter(a -> a.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}