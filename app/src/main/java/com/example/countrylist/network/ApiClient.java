package com.example.countrylist.network;

import com.example.countrylist.network.models.CountryListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {
    @GET("Country/getCountries")
    Call<CountryListResponse> getCountries();

    @GET("Country/getCountries")
    Call<CountryListResponse> searchCountry(@Query("pName") String countryName);
}
