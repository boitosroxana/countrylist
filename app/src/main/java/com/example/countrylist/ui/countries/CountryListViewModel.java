package com.example.countrylist.ui.countries;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countrylist.models.Country;
import com.example.countrylist.network.ApiClient;
import com.example.countrylist.network.RequestManager;
import com.example.countrylist.network.models.CountryListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListViewModel extends ViewModel {

   private static String TAG = CountryListViewModel.class.getSimpleName();

    private MutableLiveData<List<Country>> _countries = new MutableLiveData<List<Country>>();

    public LiveData<List<Country>> getCountries() {
        getCountryList();
        return _countries;
    }



    private void getCountryList() {
        Call<CountryListResponse> call = RequestManager.getInstance().getCountries();
        call.enqueue(new Callback<CountryListResponse>() {
            @Override
            public void onResponse(@Nullable Call<CountryListResponse> call, @Nullable Response<CountryListResponse> response) {
                if (response != null) {
                    if (response.body() != null) {
                        CountryListResponse resp = response.body();
                        _countries.setValue(resp.getResponse());
                    }

                }

            }

            @Override
            public void onFailure(Call<CountryListResponse> call, Throwable t) {

                Log.e(TAG, t.getMessage());

            }
        });

    }
}