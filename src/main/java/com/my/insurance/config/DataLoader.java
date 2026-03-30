package com.my.insurance.config;

import com.my.insurance.entity.Pricing;
import com.my.insurance.entity.TravelArea;
import com.my.insurance.repository.PricingRepository;
import com.my.insurance.repository.TravelAreaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(TravelAreaRepository repo) {
        return args -> {

            if (repo.count() == 0) {

                repo.save(create("AREA1", "Area 1",
                        "Australia, Brunei, Cambodia, China, Hong Kong, India, Indonesia, Japan, " +
                                "Korea, Laos, Macau, Maldives, Myanmar, New Zealand, Pakistan, Philippines, " +
                                "Singapore, Sri Lanka, Taiwan, Thailand and Vietnam", true));

                repo.save(create("AREA2", "Area 2",
                        "Europe, Tibet, Nepal, Mongolia, Bhutan and Countries in Area 1", true));

                repo.save(create("AREA3", "Area 3",
                        "Worldwide and countries in Area 1 and 2 but excluding Afghanistan, Cuba, " +
                                "Democratic Republic of Congo, Iran, Iraq, Sudan and Syria", true));

                repo.save(create("AREA4", "Area 4",
                        " Malaysia (single trip between Peninsular and East Malaysia and vice " +
                                "versa)", false));
            }
        };
    }

    private TravelArea create(String code, String name, String desc, boolean annual) {
        TravelArea a = new TravelArea();
        a.setCode(code);
        a.setName(name);
        a.setDescription(desc);
        a.setSupportsAnnual(annual);
        return a;
    }

    @Bean
    CommandLineRunner loadPricingData (PricingRepository repo){
        return args -> {
            if (repo.count() == 0) {
                repo.save(create("PLAN_A", "SINGLE", "AREA1", 10.0));
                repo.save(create("PLAN_A", "SINGLE", "AREA2", 15.0));
                repo.save(create("PLAN_A", "SINGLE", "AREA3", 20.0));
                repo.save(create("PLAN_A", "SINGLE", "AREA4", 5.0));

                repo.save(create("PLAN_B", "SINGLE", "AREA1", 20.0));
                repo.save(create("PLAN_B", "SINGLE", "AREA2", 30.0));
                repo.save(create("PLAN_B", "SINGLE", "AREA3", 40.0));
                repo.save(create("PLAN_B", "SINGLE", "AREA4", 10.0));

                repo.save(create("PLAN_A", "ANNUAL", "AREA1", 100.0));
                repo.save(create("PLAN_A", "ANNUAL", "AREA2", 150.0));
                repo.save(create("PLAN_A", "ANNUAL", "AREA3", 200.0));

                repo.save(create("PLAN_B", "ANNUAL", "AREA1", 150.0));
                repo.save(create("PLAN_B", "ANNUAL", "AREA2", 200.0));
                repo.save(create("PLAN_B", "ANNUAL", "AREA3", 250.0));
            }
        };
    }

    private Pricing create(String plan, String coverage, String area, double price) {
        Pricing p = new Pricing();
        p.setPlan(plan);
        p.setCoverageType(coverage);
        p.setArea(area);
        p.setPrice(price);
        return p;
    }
}