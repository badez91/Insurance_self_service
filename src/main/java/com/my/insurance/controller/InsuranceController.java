package com.my.insurance.controller;

import com.my.insurance.dto.InsuranceRequest;
import com.my.insurance.dto.InsuranceResponse;
import com.my.insurance.service.InsuranceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class InsuranceController {

    private final InsuranceService service;

    public InsuranceController(InsuranceService service) {
        this.service = service;
    }

    @PostMapping("/purchase")
    public String purchase(@ModelAttribute InsuranceRequest request, Model model) {

        InsuranceResponse response = service.purchase(request);

        model.addAttribute("policyId", response.getPolicyId());
        model.addAttribute("price", response.getTotalPrice());
        model.addAttribute("message", response.getMessage());
        model.addAttribute("request", request);

        return "summary"; // go to summary.html
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute InsuranceRequest request,
                         RedirectAttributes redirectAttributes) {

        InsuranceResponse response = service.purchase(request);

        redirectAttributes.addFlashAttribute("successMessage",
                "Application submitted successfully! Policy ID: " + response.getPolicyId());

        return "redirect:/";
    }
}