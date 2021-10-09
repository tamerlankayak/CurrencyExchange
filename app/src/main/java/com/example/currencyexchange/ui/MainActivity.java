package com.example.currencyexchange.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.currencyexchange.R;
import com.example.currencyexchange.adapter.CurrencyAdapter;
import com.example.currencyexchange.databinding.ActivityMainBinding;
import com.example.currencyexchange.db.DbConnect;
import com.example.currencyexchange.entity.CurencyRatesEntity;
import com.example.currencyexchange.entity.CurencyRatesEntityDao;
import com.example.currencyexchange.entity.DaoSession;
import com.example.currencyexchange.model.Rate;
import com.example.currencyexchange.presenter.CurrencyPresenter;
import com.example.currencyexchange.viewinterface.CurrencyView;
import com.example.currencyexchange.viewinterface.OnItemClickListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //List<CurencyRatesEntity> itemCurencyRatesEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        setUpNavigation();

    }

    public void setUpNavigation() {
        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragments);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }
}

