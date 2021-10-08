package com.example.currencyexchange.model;

import java.util.UUID;

public class Rate {

    public String name;
    public String key;
    public UUID id;

    public Rate(UUID id, String key, String name) {
        this.id = id;
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", id=" + id +
                '}';
    }
}