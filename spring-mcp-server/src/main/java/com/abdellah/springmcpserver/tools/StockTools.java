package com.abdellah.springmcpserver.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockTools {

    private List<Company> companies = List.of(
        new Company("TechCorp", 500, 1_000_000.00, "USA"),
        new Company("HealthInc", 300, 500_000.00, "Canada"),
        new Company("EduGlobal", 200, 300_000.00, "UK")
    );

    @Tool(description = "Get all companies with their details")
    public List<Company> getAllCompanies() {
        return companies;
    }

    @Tool(description = "Get company details by name")
    public Company getCompanyByName(String name) {
        return companies.stream()
            .filter(company -> company.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(()-> new RuntimeException("Company not found: " + name));
    }

    @Tool(description = "Get stock information for a company by name")
    public Stock getStockByCompanyName(String name) {
        return new Stock(name, LocalDate.now(), 600 + Math.random() * 300);
    }

}

// create a company recors that has a name, employees, turnover and  country

// create a company record that has a name, employees, turnover, and country
record Company(String name, int employees,
               @Description("Annual turnover in USD")
               double turnover, String country) {}

record Stock(String name, LocalDate date, double stock) {}