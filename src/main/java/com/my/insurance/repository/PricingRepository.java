package com.my.insurance.repository;

import com.my.insurance.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PricingRepository extends JpaRepository<Pricing, Long> {

    Optional<Pricing> findByPlanAndCoverageTypeAndArea(
            String plan, String coverageType, String area
    );
}