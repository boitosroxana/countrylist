package com.example.countrylist.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "country")
public class CountryEntity {
    @PrimaryKey
    @ColumnInfo
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String imgUrl;
    @ColumnInfo
    private String currencyCode;
    @ColumnInfo
    private String currencySymbol;
    @ColumnInfo
    private String region;
    @ColumnInfo
    private String currencyName;

    public CountryEntity(int id, String name, String imgUrl, String currencyCode, String currencySymbol, String region, String currencyName) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.currencyCode = currencyCode;
        this.currencySymbol = currencySymbol;
        this.region = region;
        this.currencyName = currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
