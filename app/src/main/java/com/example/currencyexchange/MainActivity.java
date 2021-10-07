package com.example.currencyexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.currencyexchange.databinding.ActivityMainBinding;
import com.example.currencyexchange.network.ApiClient;
import com.example.currencyexchange.pojo.Currency;
import com.example.currencyexchange.services.ApiServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    ApiServices services;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        services = ApiClient.getClient().create(ApiServices.class);

        services.getCurrencyList().toObservable().subscribeOn(Schedulers.io()).subscribe(new Observer<Currency>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Currency currency) {
                binding.textviewAzn.setText("AZN "+currency.getRates().getAzn());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Error", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }
}

//api key 98108816f30be67d1a9134ef6c949507