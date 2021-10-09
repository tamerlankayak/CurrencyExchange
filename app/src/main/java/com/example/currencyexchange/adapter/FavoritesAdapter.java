package com.example.currencyexchange.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyexchange.R;
import com.example.currencyexchange.entity.CurencyRatesEntity;
import com.example.currencyexchange.viewinterface.OnDbItemClickListener;

public class FavoritesAdapter extends ListAdapter<CurencyRatesEntity, FavoritesAdapter.FavoritesViewHolder> {

    private final OnDbItemClickListener listener;


    public FavoritesAdapter(OnDbItemClickListener listener) {
        super(FavoritesAdapter.itemCallback);
        this.listener = listener;
    }

    class FavoritesViewHolder extends RecyclerView.ViewHolder {
        TextView currencyName = itemView.findViewById(R.id.textview_favorit_currency_name);

        TextView currencyAmount = itemView.findViewById(R.id.textview_favorit_currency_amount);

        CurencyRatesEntity curencyRatesEntity = null;

        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(CurencyRatesEntity rate, final OnDbItemClickListener listener) {
            curencyRatesEntity = rate;
            currencyName.setText(rate.getCurrencyName());
            currencyAmount.setText("" + rate.getCurrencyAmount());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onItemClick(rate);
                    return true;
                }
            });
        }
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_favorites, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        CurencyRatesEntity rates = getItem(position);
        holder.bind(rates, listener);
    }


    public static DiffUtil.ItemCallback<CurencyRatesEntity> itemCallback = new DiffUtil.ItemCallback<CurencyRatesEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull CurencyRatesEntity oldItem, @NonNull CurencyRatesEntity newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull CurencyRatesEntity oldItem, @NonNull CurencyRatesEntity newItem) {
            return oldItem.getId() == newItem.getId();
        }
    };
}