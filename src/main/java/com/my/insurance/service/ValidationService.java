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
}
