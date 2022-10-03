package com.nighthawk.spring_portfolio.controllers;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConversion {
  public Map<String, Double> convertFrom (String currency, Double amount) {
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