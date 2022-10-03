package com.nighthawk.spring_portfolio.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class CurrencyConverter {

    @GetMapping("/currencyconvert")
    public String greeting(@RequestParam(name="currency", required=false) String currency, @RequestParam(name="amount", required=false) Double amount, Model model) {
        if (currency != null && amount != null) {
          CurrencyConversion converter = new CurrencyConversion();
          Map<String, Double> currencyResults = converter.convertFrom(currency, amount);
          model.addAttribute("results", currencyResults);
        }
        return "currencyconvert"; 
    }
}