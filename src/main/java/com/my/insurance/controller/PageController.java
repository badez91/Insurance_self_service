package com.my.insurance.controller;

import com.my.insurance.entity.TravelArea;
import com.my.insurance.service.PricingService;
import com.my.insurance.service.TravelAreaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class PageController {

    private final PricingService pricingService;

    private final TravelAreaService areaService;

    public PageController(TravelAreaService areaService, PricingService pricingService) {
        this.areaService = areaService;
        this.pricingService = pricingService;
    }

    @GetMapping("/")
    public String coverage(Model model) {
        model.addAttribute("areas", areaService.getAll());
        return "coverage";
    }

    @GetMapping("/coverage") // 🔥 ADD THIS
    public String coveragePage(Model model) {
        model.addAttribute("areas", areaService.getAll());
        return "coverage";
    }

    @PostMapping("/plan")
    public String plan(@RequestParam String coverageType,
                       @RequestParam String area,
                       @RequestParam String startDate,
                       @RequestParam String endDate,
                       Model model) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        Map<String, Double> prices =
                pricingService.calculatePlans(coverageType, area, start, end);

        TravelArea areaObj = areaService.getByCode(area);

        model.addAttribute("coverageType", coverageType);
        model.addAttribute("areaCode", area);
        model.addAttribute("areaName", areaObj.getName());
        model.addAttribute("areaDesc", areaObj.getDescription());

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        model.addAttribute("priceA", prices.get("PLAN_A"));
        model.addAttribute("priceB", prices.get("PLAN_B"));

        return "plan";
    }

    @PostMapping("/customer")
    public String customer(@RequestParam String plan,
                           @RequestParam String coverageType,
                           @RequestParam String area,
                           @RequestParam String startDate,
                           @RequestParam String endDate,
                           Model model) {

        Map<String, Double> prices =
                pricingService.calculatePlans(coverageType, area,
                        LocalDate.parse(startDate),
                        LocalDate.parse(endDate));

        double selectedPrice = prices.get(plan);
        TravelArea areaObj = areaService.getByCode(area);

        model.addAttribute("plan", plan);
        model.addAttribute("price", selectedPrice);

        model.addAttribute("coverageType", coverageType);
        model.addAttribute("areaCode", area);
        model.addAttribute("areaName", areaObj.getName());
        model.addAttribute("areaDesc", areaObj.getDescription());
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "customer";
    }

    @PostMapping("/summary")
    public String summary(@RequestParam Map<String, String> data, Model model) {

        String areaCode = data.get("area");

        TravelArea areaObj = areaService.getByCode(areaCode);

        model.addAttribute("data", data);
        model.addAttribute("areaName", areaObj.getName());
        model.addAttribute("areaDesc", areaObj.getDescription());

        return "summary";
    }
}