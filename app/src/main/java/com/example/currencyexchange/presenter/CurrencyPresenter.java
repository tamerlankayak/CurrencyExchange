package com.example.currencyexchange.presenter;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.example.currencyexchange.db.DbConnect;
import com.example.currencyexchange.entity.CurencyRatesEntity;
import com.example.currencyexchange.entity.CurencyRatesEntityDao;
import com.example.currencyexchange.entity.DaoSession;
import com.example.currencyexchange.model.Rate;
import com.example.currencyexchange.network.ApiClient;
import com.example.currencyexchange.pojo.CurrencyPOJO;
import com.example.currencyexchange.services.ApiServices;
import com.example.currencyexchange.viewinterface.CurrencyView;
import com.example.currencyexchange.viewinterface.FavoritesView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CurrencyPresenter {
    private CurrencyView currencyView;

    @Inject
    public CurrencyPresenter(CurrencyView currencyView) {
        this.currencyView = currencyView;
    }


    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Inject
    ApiServices services;

    List<String> countries = new ArrayList<>();

    List<Rate> rates = new ArrayList<>();

//For async operations using RxJava libraru
    public void getCurrencyByCountry(String countryName) {
        currencyView.showProgressBar();
        rates.clear();
        countries.clear();
        services = ApiClient.getClient().create(ApiServices.class);

        mDisposable.add(services.getCurrencyByCountry(countryName)
                .subscribeOn(Schedulers.io())
                .doOnError(throwable -> Log.e("Error", throwable.getLocalizedMessage()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CurrencyPOJO>() {
                    @Override
                    public void accept(CurrencyPOJO currencyPOJO) throws Throwable {
                        for (Map.Entry entry : currencyPOJO.rates.entrySet()) {
                            rates.add(new Rate(UUID.randomUUID(), entry.getKey().toString(), entry.getValue().toString()));
                            countries.add(entry.getKey().toString());
                        }

                        currencyView.setDataToRecyclerView(rates, countries);
                        currencyView.hideProgressBar();
                    }
                }));
    }


}
