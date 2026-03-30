package com.my.insurance.service;

import com.my.insurance.entity.Pricing;
import com.my.insurance.repository.PricingRepository;
import com.my.insurance.util.PricingEnum;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.Optional;

import static com.my.insurance.util.PricingEnum.ANNUAL;
import static com.my.insurance.util.PricingEnum.SINGLE;

@Service
public class PricingService {

    private final PricingRepository repo;

    public PricingService(PricingRepository repo) {
        this.repo = repo;
    }

    public double getPrice(String plan,
                           String coverageType,
                           String area,
                           LocalDate start,
                           LocalDate end) {

        Optional<Pricing> dbPrice =
                repo.findByPlanAndCoverageTypeAndArea(plan, coverageType, area);

        double basePrice;

        if (dbPrice.isPresent()) {
            basePrice = dbPrice.get().getPrice();
        } else {
            // 🔥 fallback
            String key = plan + "_" + area;

            if ("SINGLE".equals(coverageType)) {
                basePrice = SINGLE.get(key);
            } else {
                basePrice = ANNUAL.get(key);
            }
        }

        if ("SINGLE".equals(coverageType)) {
            long days = ChronoUnit.DAYS.between(start, end) + 1;
            return basePrice * days;
        }

        return basePrice;
    }

    public double calculate(String plan, String area, String coverageType,
                            LocalDate start, LocalDate end) {

        String planCode = plan.split("_")[1]; // PLAN_A → A
        String key = planCode + "_" + area;

        if (coverageType.equals("SINGLE")) {
            long days = ChronoUnit.DAYS.between(start, end) + 1;
            return SINGLE.get(key) * days;
        } else {
            return ANNUAL.get(key);
        }
    }

    public Map<String, Double> calculatePlans(String coverageType,
                                              String area,
                                              LocalDate start,
                                              LocalDate end) {

        Map<String, Double> result = new HashMap<>();

        for (String plan : List.of("PLAN_A", "PLAN_B")) {
            result.put(plan, getPrice(plan, coverageType, area, start, end));
        }

        return result;
    }
}