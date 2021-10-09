package com.example.currencyexchange.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.currencyexchange.R;
import com.example.currencyexchange.adapter.CurrencyAdapter;
import com.example.currencyexchange.databinding.FragmentCurrencyBinding;
import com.example.currencyexchange.databinding.FragmentFavoritesBinding;
import com.example.currencyexchange.db.DbConnect;
import com.example.currencyexchange.entity.CurencyRatesEntity;
import com.example.currencyexchange.entity.CurencyRatesEntityDao;
import com.example.currencyexchange.entity.DaoSession;
import com.example.currencyexchange.model.Rate;
import com.example.currencyexchange.presenter.CurrencyPresenter;
import com.example.currencyexchange.viewinterface.CurrencyView;
import com.example.currencyexchange.viewinterface.OnItemClickListener;

import java.util.List;

public class CurrencyFragment extends Fragment  implements CurrencyView {

    private FragmentCurrencyBinding binding;
    CurrencyAdapter currencyAdapter;
    RecyclerView recyclerView;
    CurrencyPresenter currencyPresenter;
    CurencyRatesEntityDao curencyRatesEntityDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currencyPresenter = new CurrencyPresenter(this);
        currencyPresenter.getCurrencyByCountry("azn");

        DaoSession daoSession = ((DbConnect) requireActivity().getApplication()).getDaoSession();
        curencyRatesEntityDao = daoSession.getCurencyRatesEntityDao();


        binding.autoCompleteTextViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currencyPresenter.getCurrencyByCountry(adapterView.getItemAtPosition(i).toString());
            }
        });
    }


    @Override
    public void setDataToRecyclerView(List<Rate> rates, List<String> countries) {
        recyclerView = binding.recylerviewCurrency;
        currencyAdapter = new CurrencyAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(Rate rate) {
                CurencyRatesEntity curencyRatesEntity = new CurencyRatesEntity();
                curencyRatesEntity.setCurrencyName(rate.getKey());
                curencyRatesEntity.setCurrencyAmount(rate.getName());
                curencyRatesEntityDao.save(curencyRatesEntity);
                Toast.makeText(requireActivity().getApplicationContext(), "Currency added to favorites successfully", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(currencyAdapter);
        currencyAdapter.submitList(rates);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_dropdown_item_1line, countries);
        binding.autoCompleteTextViewCountries.setAdapter(arrayAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}