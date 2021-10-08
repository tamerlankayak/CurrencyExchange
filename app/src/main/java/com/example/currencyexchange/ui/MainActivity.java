package com.example.currencyexchange.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.currencyexchange.adapter.CurrencyAdapter;
import com.example.currencyexchange.databinding.ActivityMainBinding;
import com.example.currencyexchange.network.ApiClient;
import com.example.currencyexchange.pojo.Currency;
import com.example.currencyexchange.model.Rate;
import com.example.currencyexchange.services.ApiServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ApiServices services;
    private ActivityMainBinding binding;
    List<String> countries = new ArrayList<>();
    List<Rate> rates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);


        services = ApiClient.getClient().create(ApiServices.class);


        services.getCurrencyList().toObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Currency>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Currency currency) {
                RecyclerView recyclerView = binding.recylerviewCurrency;
                CurrencyAdapter currencyAdapter = new CurrencyAdapter();
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);

                recyclerView.setAdapter(currencyAdapter);


                //binding.textviewAzn.setText("AZN " + currency.getRates().getAzn());
                //currency.rates.forEach(key -> rates.add(new Rate(key, currency.rates.get()));

                for (Map.Entry entry : currency.rates.entrySet()) {
                    rates.add(new Rate(UUID.randomUUID(), entry.getKey().toString(), entry.getValue().toString()));
                    countries.add(entry.getKey().toString());
                }
                currencyAdapter.submitList(rates);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Error", e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countries);
        binding.autoCompleteTextViewCountries.setAdapter(arrayAdapter);
        //binding.spinnerCountries.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e("position", "" + i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
    }
}

//api key 98108816f30be67d1a9134ef6c949507