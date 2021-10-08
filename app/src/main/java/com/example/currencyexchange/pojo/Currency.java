package com.example.currencyexchange.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;


public class Currency {

    @SerializedName("rates")
    public Map<String, Double> rates;

}
