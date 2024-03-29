package com.example.currencyexchange.viewinterface;

import com.example.currencyexchange.entity.CurencyRatesEntity;
import com.example.currencyexchange.model.Rate;

import dagger.Component;

@Component
public interface OnItemClickListener {
    void onItemClick(Rate rate);
}
