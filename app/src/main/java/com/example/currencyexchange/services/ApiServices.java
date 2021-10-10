package com.example.currencyexchange.services;

import com.example.currencyexchange.pojo.CurrencyPOJO;

import dagger.Component;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiServices {

    @GET("latest")
    Flowable<CurrencyPOJO> getCurrencyList();

    @GET("latest")
    Flowable<CurrencyPOJO> getCurrencyByCountry(@Query("base") String countryName);
}
