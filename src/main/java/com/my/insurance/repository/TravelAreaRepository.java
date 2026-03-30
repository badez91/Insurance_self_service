package com.my.insurance.repository;

import com.my.insurance.entity.TravelArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelAreaRepository extends JpaRepository<TravelArea, Long> {

    List<TravelArea> findBySupportsAnnualTrue();

}
