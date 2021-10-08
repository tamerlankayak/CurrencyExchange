package com.example.currencyexchange.services;

import com.example.currencyexchange.pojo.Currency;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET(".")
    Flowable<Currency> getCurrencyList();

    @GET(".")
    Flowable<Currency> getCurrencyByCountry(@Query("base") String countryName);
}
