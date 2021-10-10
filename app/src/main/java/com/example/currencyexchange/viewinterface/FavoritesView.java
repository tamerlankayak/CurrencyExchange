package com.example.currencyexchange.viewinterface;

import com.example.currencyexchange.entity.CurencyRatesEntity;

import java.util.List;

import dagger.Component;

public interface FavoritesView {
    void showProgressBar();

    void hideProgressBar();

    void setDataToFavoritsAdapter(List<CurencyRatesEntity>curencyRatesEntities);
}
