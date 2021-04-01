package com.example.countrylist.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.countrylist.database.entities.CountryEntity;
import com.example.countrylist.models.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM country")
    List<CountryEntity> getFavouriteCountries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addToFavourite(CountryEntity country);

    @Delete
    void deleteFromFavourites(CountryEntity country);

    @Query("DELETE FROM country")
    void removeAll();
}
