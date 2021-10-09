package com.example.currencyexchange.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb="Currency")
public class CurencyRatesEntity {

    @Id(autoincrement = true)
    private Long id;

    private String currencyName;
    private String currencyAmount;
    @Generated(hash = 1180907079)
    public CurencyRatesEntity(Long id, String currencyName, String currencyAmount) {
        this.id = id;
        this.currencyName = currencyName;
        this.currencyAmount = currencyAmount;
    }
    @Generated(hash = 1350284083)
    public CurencyRatesEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCurrencyName() {
        return this.currencyName;
    }
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
    public String getCurrencyAmount() {
        return this.currencyAmount;
    }
    public void setCurrencyAmount(String currencyAmount) {
        this.currencyAmount = currencyAmount;
    }
    
}
