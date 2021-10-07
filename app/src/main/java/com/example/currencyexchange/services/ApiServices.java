package com.example.currencyexchange.services;

import com.example.currencyexchange.pojo.Currency;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("latest")
    Flowable<Currency> getCurrencyList();

}
