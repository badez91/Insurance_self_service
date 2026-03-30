package com.my.insurance.service;

import com.my.insurance.dto.InsuranceRequest;
import com.my.insurance.exception.CustomExceptionHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ValidationService {

    public void validate(InsuranceRequest req) {

        if (!req.getNric().matches("\\d{12}"))
            throw new CustomExceptionHandler("Invalid NRIC");

        if (!req.getEmail().matches(".+@.+\\..+"))
            throw new CustomExceptionHandler("Invalid email");

        if (!req.getMobile().matches("01\\d{7,9}"))
            throw new CustomExceptionHandler("Invalid mobile");

        if (!req.getPostcode().matches("\\d{5}"))
            throw new CustomExceptionHandler("Invalid postcode");

        if (req.getCoverageType().equals("ANNUAL") && req.getArea().equals("AREA_4"))
            throw new CustomExceptionHandler("Area 4 not allowed for annual");

        LocalDate start = req.getStartDate();
        LocalDate end = req.getEndDate();

        if (start.isBefore(LocalDate.now()))
            throw new CustomExceptionHandler("Start date invalid");

        if (req.getCoverageType().equals("SINGLE")) {
            long days = ChronoUnit.DAYS.between(start, end);
            if (days > 180)
                throw new CustomExceptionHandler("Max 180 days");
        }

    }
    public void validateDates(LocalDate start, LocalDate end, String coverageType) {

        if (start == null || end == null) {
            throw new CustomExceptionHandler("Start date and End date are required");
        }

        // ❗ Main rule
        if (start.isAfter(end)) {
            throw new CustomExceptionHandler("Start date cannot be after End date");
        }

        // Optional: no past date
        if (start.isBefore(LocalDate.now())) {
            throw new CustomExceptionHandler("Start date cannot be in the past");
        }

        // Rule: max 1 year ahead
        if (start.isAfter(LocalDate.now().plusYears(1))) {
            throw new CustomExceptionHandler("Start date cannot exceed 1 year from today");
        }

        // Rule: Single Trip max 180 days
        if ("SINGLE".equals(coverageType)) {
            long days = ChronoUnit.DAYS.between(start, end);
            if (days > 180) {
                throw new CustomExceptionHandler("Single trip cannot exceed 180 days");
            }
        }

        // Rule: Annual → auto enforce 1 year
        if ("ANNUAL".equals(coverageType)) {
            if (!end.equals(start.plusYears(1).minusDays(1))) {
                throw new CustomExceptionHandler("Annual coverage must be exactly 1 year");
            }
        }
    }
}
