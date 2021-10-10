package com.example.currencyexchange.viewinterface;

import com.example.currencyexchange.model.Rate;

import java.util.List;

import dagger.Component;

public interface CurrencyView {
    void setDataToRecyclerView(List<Rate> rates, List<String> countries);
    void showProgressBar();
    void hideProgressBar();
}
