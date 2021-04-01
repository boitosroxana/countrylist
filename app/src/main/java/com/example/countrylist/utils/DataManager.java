package com.example.countrylist.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.countrylist.models.User;

public class DataManager {
    private static DataManager mInstance;

    private SharedPreferences mSharedPreferences;
    private String username;
    private String email;
    private String password;
    private boolean isLoggedIn = false;


    private DataManager() {

    }

    public static synchronized DataManager getInstance() {
        if (mInstance == null)
            mInstance = new DataManager();

        return mInstance;
    }

    public static void setString(String username, String password, String email) {
    }


    public void init(Application app) {
        if (mSharedPreferences != null) return;

        mSharedPreferences = app.getSharedPreferences("CountryList", Context.MODE_PRIVATE);
    }

    public synchronized void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    public synchronized void setString(String key, @Nullable String value) {
        if (TextUtils.isEmpty(value)) mSharedPreferences.edit().remove(key).apply();
        else {
            mSharedPreferences.edit().putString(key, value).apply();
        }
    }

    public synchronized String getString(String key, @Nullable String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    public synchronized void setInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public synchronized int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    public synchronized void setBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public synchronized boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }


    public synchronized void setLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).apply();
    }

    public synchronized long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    public synchronized void setLoggedIn(boolean value) {
        setLoggedIn(value);
        setBoolean("isLoggedIn", getLoggedIn());
    }

    public synchronized boolean getLoggedIn() {
        return getBoolean("isLoggedIn",false);
    }

    public synchronized void signUp(User user){
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setString("username",getUsername());
        setString("email",getEmail());
        setString("password",getPassword());
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getUsername() {
        return username;
    }

  private void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}