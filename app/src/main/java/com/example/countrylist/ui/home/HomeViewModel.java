package com.example.countrylist.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.countrylist.database.CountryDatabase;
import com.example.countrylist.database.dao.CountryDao;
import com.example.countrylist.database.entities.CountryEntity;
import com.example.countrylist.database.mapper.CountryMapper;
import com.example.countrylist.models.Country;
import com.example.countrylist.utils.DataManager;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private CountryMapper mapper = new CountryMapper();
    private MutableLiveData<List<Country>> _countries = new MutableLiveData<List<Country>>();

    public LiveData<List<Country>> getCountries(Context context) {
        getCountryList(context);
        return _countries;
    }


    private void getCountryList(Context context) {
        CountryDao db = CountryDatabase.getInstance(context).countryDao();
        List<Country> countries = new ArrayList<>();
        for (CountryEntity entity :
                db.getFavouriteCountries()) {
            Country fromdb = mapper.map(entity);
            countries.add(fromdb);
        }
        //if (DataManager.getInstance().getBoolean("isLoggedIn", false))
            _countries.setValue(countries);
        //else _countries.setValue(new ArrayList<Country>());
    }
}