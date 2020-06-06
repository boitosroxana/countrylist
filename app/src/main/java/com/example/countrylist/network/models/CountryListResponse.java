package com.example.countrylist.network.models;

import com.example.countrylist.models.Country;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryListResponse {

    @SerializedName("IsSucces")
    private boolean isSucces;
    @SerializedName("UserMessage")
    private String userMessage;
    @SerializedName("TechnicalMessage")
    private String technicalMessage;
    @SerializedName("TotalCount")
    private int totalCount;
    @SerializedName("Response")
    private List<Country> response;

    public CountryListResponse(boolean isSucces, String userMessage, String technicalMessage, int totalCount, List<Country> response) {
        this.isSucces = isSucces;
        this.userMessage = userMessage;
        this.technicalMessage = technicalMessage;
        this.totalCount = totalCount;
        this.response = response;
    }

    public boolean isSucces() {
        return isSucces;
    }

    public void setSucces(boolean succes) {
        isSucces = succes;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public void setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Country> getResponse() {
        return response;
    }

    public void setResponse(List<Country> response) {
        this.response = response;
    }
}
