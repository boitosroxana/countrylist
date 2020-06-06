package com.example.countrylist.models;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("NumericCode")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("FlagPng")
    private String imgUrl;
    @SerializedName("CurrencyCode")
    private String currencyCode;
    @SerializedName("CurrencySymbol")
    private String currencySymbol;
    @SerializedName("Region")
    private String region;

    public Country(int id, String name, String imgUrl, String currencyCode, String currencySymbol, String region) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.currencyCode = currencyCode;
        this.currencySymbol = currencySymbol;
        this.region = region;
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

    //    "Name":"American Samoa",
//            "Alpha2Code":"AS",
//            "Alpha3Code":"ASM",
//            "NativeName":"American Samoa",
//            "Region":"Oceania",
//            "SubRegion":"Polynesia",
//            "Latitude":"-14.33333333",
//            "Longitude":"-170",
//            "Area":199,
//            "NumericCode":16,
//            "NativeLanguage":"eng",
//            "CurrencyCode":"USD",
//            "CurrencyName":"United State Dollar",
//            "CurrencySymbol":"$",
//            "Flag":"https://api.backendless.com/2F26DFBF-433C-51CC-FF56-830CEA93BF00/473FB5A9-D20E-8D3E-FF01-E93D9D780A00/files/CountryFlags/asm.svg",
//            "FlagPng":"https://api.backendless.com/2F26DFBF-433C-51CC-FF56-830CEA93BF00/473FB5A9-D20E-8D3E-F
}
