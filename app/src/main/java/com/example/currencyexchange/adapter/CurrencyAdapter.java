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
import com.example.currencyexchange.model.Rate;

public class CurrencyAdapter extends ListAdapter<Rate, CurrencyAdapter.CurrencyViewHolder> {

    public CurrencyAdapter() {
        super(CurrencyAdapter.itemCallback);
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView currencyName = itemView.findViewById(R.id.textview_currency_name);

        TextView currencyAmount = itemView.findViewById(R.id.textview_currency_amount);

        Rate currentRate = null;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Rate rate) {
            currentRate = rate;
            currencyName.setText(rate.getKey());
            currencyAmount.setText("" + rate.getName());
        }
    }


    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_currency, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        Rate rates = getItem(position);
        holder.bind(rates);
    }



    public static DiffUtil.ItemCallback<Rate> itemCallback = new DiffUtil.ItemCallback<Rate>() {
        @Override
        public boolean areItemsTheSame(@NonNull Rate oldItem, @NonNull Rate newItem) {
            return oldItem == newItem;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Rate oldItem, @NonNull Rate newItem) {
            return oldItem.id == newItem.id;
        }
    };
}
