package com.nighthawk.spring_portfolio.controllers;

import java.util.Currency;
import java.util.HashMap;
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
          Map<String, Double> currencyResults = CurrencyConversion.convertFrom(currency, amount);
          model.addAttribute("results", currencyResults);
        }
        return "currencyconvert"; 
    }

    static class CurrencyConversion {
      public static Map<String, Double> convertFrom (String currency, Double amount) {
        Map<String, Double> rates = getRatesFor(currency);
        Map<String, Double> ret = new HashMap<String, Double>();
        for (String key : rates.keySet()) {
          ret.put(key, rates.get(key) * amount);
        }
        return ret;
      }

      public static Map<String, Double> getRatesFor (String currency) {
        Map<String, Double> ret = new HashMap<String, Double>();
        // TODO: Use Rapid API
        ret.put("EUR", 1.01);
        ret.put("GBP", 1.07);
        return ret;
      }
    }

}