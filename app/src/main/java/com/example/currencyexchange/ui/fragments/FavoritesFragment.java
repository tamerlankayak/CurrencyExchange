package com.example.currencyexchange.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyexchange.adapter.FavoritesAdapter;
import com.example.currencyexchange.databinding.FragmentFavoritesBinding;
import com.example.currencyexchange.db.DbConnect;
import com.example.currencyexchange.entity.CurencyRatesEntity;
import com.example.currencyexchange.entity.CurencyRatesEntityDao;
import com.example.currencyexchange.entity.DaoSession;
import com.example.currencyexchange.viewinterface.OnDbItemClickListener;

import java.util.List;


public class FavoritesFragment extends Fragment {
    private FragmentFavoritesBinding binding;
    CurencyRatesEntityDao curencyRatesEntityDao;
    List<CurencyRatesEntity> favoritesList;

    FavoritesAdapter favoritesAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaoSession daoSession = ((DbConnect) requireActivity().getApplication()).getDaoSession();
        curencyRatesEntityDao = daoSession.getCurencyRatesEntityDao();

        favoritesList = curencyRatesEntityDao.queryBuilder().list();

        for (CurencyRatesEntity itemfav : favoritesList) {
            Log.e("TAG", "onViewCreated: " + itemfav.getCurrencyAmount());
        }

        recyclerView = binding.recyclerviewFavorites;

        favoritesAdapter = new FavoritesAdapter(new OnDbItemClickListener() {
            @Override
            public void onItemClick(CurencyRatesEntity rate) {
                CurencyRatesEntity curencyRatesEntity = new CurencyRatesEntity();
                curencyRatesEntity.setCurrencyName(rate.getCurrencyName());
                curencyRatesEntity.setCurrencyAmount(rate.getCurrencyAmount());
                curencyRatesEntityDao.save(curencyRatesEntity);
                Toast.makeText(requireActivity().getApplicationContext(), "Currency added to favorites successfully", Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(favoritesAdapter);
        favoritesAdapter.submitList(favoritesList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}