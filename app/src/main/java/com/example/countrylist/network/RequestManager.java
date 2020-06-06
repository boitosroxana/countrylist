package com.example.countrylist.network;

import com.example.countrylist.network.models.CountryListResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.countrylist.utils.Constants.BASE_URL;

public class RequestManager {

    private static RequestManager instance;

    private ApiClient apiClient;

    private RequestManager() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();

        apiClient = retrofit.create(ApiClient.class);
    }

    public static RequestManager getInstance() {
        if (instance == null) {
            instance = new RequestManager();
        }
        return instance;
    }


        public Call<CountryListResponse> getCountries(){return apiClient.getCountries();}
        public Call<CountryListResponse> searchCountry(String countryName){return apiClient.searchCountry(countryName);}
}