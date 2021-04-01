package com.example.countrylist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.countrylist.database.dao.CountryDao;
import com.example.countrylist.database.entities.CountryEntity;

@Database(entities = {CountryEntity.class}, version = 1, exportSchema = false)
public abstract class CountryDatabase extends RoomDatabase {

    
    public abstract CountryDao countryDao();
    private static CountryDatabase instance;


    public static CountryDatabase getInstance(Context context){
        if(instance == null){
            return Room.databaseBuilder(context, CountryDatabase.class,"app_database")
                    .allowMainThreadQueries().fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
