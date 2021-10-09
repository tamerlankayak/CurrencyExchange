package com.example.currencyexchange.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;


public class CurrencyPOJO {

    @SerializedName("rates")
    public Map<String, Double> rates;

}
