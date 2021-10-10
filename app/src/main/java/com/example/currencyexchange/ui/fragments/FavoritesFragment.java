package com.example.currencyexchange.ui.fragments;

import android.os.Bundle;
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
import com.example.currencyexchange.entity.CurencyRatesEntity;
import com.example.currencyexchange.entity.CurencyRatesEntityDao;
import com.example.currencyexchange.presenter.FavoritePresenter;
import com.example.currencyexchange.viewinterface.FavoritesView;

import java.util.List;


public class FavoritesFragment extends Fragment implements FavoritesView{
    private FragmentFavoritesBinding binding;
    CurencyRatesEntityDao curencyRatesEntityDao;

    FavoritesAdapter favoritesAdapter;
    RecyclerView recyclerView;
    FavoritePresenter favoritePresenter;

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
        favoritePresenter = new FavoritePresenter(this);
        favoritePresenter.getDataFromDb(this.getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showProgressBar() {
        binding.progressBar2.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar2.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToFavoritsAdapter(List<CurencyRatesEntity> curencyRatesEntities) {

        recyclerView = binding.recyclerviewFavorites;

        favoritesAdapter = new FavoritesAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(binding.getRoot().getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(favoritesAdapter);
        favoritesAdapter.submitList(curencyRatesEntities);
    }


}