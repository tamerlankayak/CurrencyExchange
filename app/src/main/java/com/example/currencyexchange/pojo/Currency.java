package com.example.currencyexchange.pojo;

import com.google.gson.annotations.SerializedName;


public class Currency {

    @SerializedName("rates")
    public Rates rates;

    public Rates getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "rates=" + rates +
                '}';
    }

    public class Rates {

        @SerializedName("AZN")
        public double azn;

        @SerializedName("TRY")
        public double turkishLira;

        @SerializedName("USD")
        public double usd;

        @SerializedName("EUR")
        public double eur;

        @SerializedName("RUB")
        public double rub;

        @SerializedName("CAD")
        public double cad;

        @SerializedName("JPY")
        public double jpy;

        @SerializedName("CZK")
        public double czk;

        public double getAzn() {
            return azn;
        }

        public double getTurkishLira() {
            return turkishLira;
        }

        public double getUsd() {
            return usd;
        }

        public double getEur() {
            return eur;
        }

        public double getRub() {
            return rub;
        }

        public double getCad() {
            return cad;
        }

        public double getJpy() {
            return jpy;
        }

        public double getCzk() {
            return czk;
        }

        @Override
        public String toString() {
            return "Rates{" +
                    "azn=" + azn +
                    ", turkishLira=" + turkishLira +
                    ", usd=" + usd +
                    ", eur=" + eur +
                    ", rub=" + rub +
                    ", cad=" + cad +
                    ", jpy=" + jpy +
                    ", czk=" + czk +
                    '}';
        }
    }




}
