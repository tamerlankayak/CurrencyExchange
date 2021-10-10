package com.example.currencyexchange.presenter;

import androidx.fragment.app.FragmentActivity;

import com.example.currencyexchange.db.DbConnect;
import com.example.currencyexchange.entity.CurencyRatesEntity;
import com.example.currencyexchange.entity.CurencyRatesEntityDao;
import com.example.currencyexchange.entity.DaoSession;
import com.example.currencyexchange.viewinterface.FavoritesView;

import java.util.List;

import javax.inject.Inject;

public class FavoritePresenter {
    private FavoritesView favoritesView;
    CurencyRatesEntityDao curencyRatesEntityDao;
    List<CurencyRatesEntity> listcurencyRatesEntity;

    public FavoritePresenter(FavoritesView favoritesView) {
        this.favoritesView = favoritesView;
    }

    public void getDataFromDb(FragmentActivity activity) {
        favoritesView.showProgressBar();
        DaoSession daoSession=((DbConnect) activity.getApplication()).getDaoSession();
        curencyRatesEntityDao=daoSession.getCurencyRatesEntityDao();
        listcurencyRatesEntity=curencyRatesEntityDao.queryBuilder().list();
        favoritesView.setDataToFavoritsAdapter(listcurencyRatesEntity);
        favoritesView.hideProgressBar();
    }
}
