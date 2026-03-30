package com.my.insurance.service;

import com.my.insurance.dto.InsuranceRequest;
import com.my.insurance.dto.InsuranceResponse;
import com.my.insurance.entity.Customer;
import com.my.insurance.entity.Policy;
import com.my.insurance.repository.CustomerRepository;
import com.my.insurance.repository.PolicyRepository;
import org.springframework.stereotype.Service;
import com.my.insurance.util.NricUtil;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsuranceService {

    private final CustomerRepository customerRepo;
    private final PolicyRepository policyRepo;
    private final ValidationService validationService;
    private final PricingService pricingService;

    public InsuranceService(CustomerRepository c, PolicyRepository p,
                            ValidationService v, PricingService pr) {
        this.customerRepo = c;
        this.policyRepo = p;
        this.validationService = v;
        this.pricingService = pr;
    }

    @Transactional
    public InsuranceResponse purchase(InsuranceRequest req) {

        validationService.validate(req);

        Customer customer = new Customer();
        customer.setFullName(req.getFullName());
        customer.setNric(req.getNric());
        customer.setEmail(req.getEmail());
        customer.setMobile(req.getMobile());
        customer.setAddress1(req.getAddress1());
        customer.setAddress2(req.getAddress2());
        customer.setPostcode(req.getPostcode());

        var dob = NricUtil.extractDob(req.getNric());
        customer.setDateOfBirth(dob != null ? dob.toString() : null);
        customer.setGender(NricUtil.extractGender(req.getNric()));

        customerRepo.save(customer);

        double price = pricingService.calculate(
                req.getPlan(),
                req.getCoverageType(),
                req.getArea(),
                req.getStartDate(),
                req.getEndDate()
        );

        Policy policy = new Policy();
        policy.setCustomer(customer);
        policy.setCoverageType(req.getCoverageType());
        policy.setArea(req.getArea());
        policy.setPlan(req.getPlan());
        policy.setStartDate(req.getStartDate());
        policy.setEndDate(req.getEndDate());
        policy.setTotalPrice(price);

        policyRepo.save(policy);

        return new InsuranceResponse(policy.getId(), price, "Application submitted successfully");
    }
}