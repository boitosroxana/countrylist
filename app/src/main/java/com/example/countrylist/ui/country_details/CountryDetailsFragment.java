package com.example.countrylist.ui.country_details;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.countrylist.R;
import com.example.countrylist.models.Country;
import com.example.countrylist.utils.ImageLoader;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.logging.Logger;

public class CountryDetailsFragment extends Fragment {

    private Country country;
    private ImageView flag;
    private TextView name;
    private TextView currencyCode;
    private TextView currencyName;
    private TextView currencySymbol;
    private TextView region;

    public static CountryDetailsFragment newInstance() {
        return new CountryDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.country_details_fragment, container, false);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadViews(view);
        getCountryFromBundle();
    }

    private void getCountryFromBundle(){
        Bundle args = getArguments();
        String szCountry = "";
        if (args!=null){
            Country newCountry;
            szCountry = args.getString("country");
            newCountry = new Gson().fromJson(szCountry, Country.class);
            Log.e("CountryDetails", newCountry.getName());
            updateCountry(newCountry);
        }
    }

    private void updateCountry(Country mCountry){
        country = mCountry;
        setViews(country);
    }

    private void loadViews(View view){
        flag = view.findViewById(R.id.iv_flag);
        name = view.findViewById(R.id.name);
        currencyCode = view.findViewById(R.id.tv_currency_code);
        currencyName = view.findViewById(R.id.tv_currency_name);
        currencySymbol = view.findViewById(R.id.tv_currency_symbol);
        region = view.findViewById(R.id.tv_region);
    }

    private void setViews(Country country){
        String strRegion, code, symbol, cName = "";
        if (country!=null){
            name.setText(country.getName());
            ImageLoader.loadImageUrl(flag, country.getImgUrl(), getContext());
            strRegion = "Region: "+country.getRegion();
            code = "Currency code: "+country.getCurrencyCode();
            symbol = "Currency symbol: "+country.getCurrencySymbol();
            cName = "Currency name: "+country.getCurrencyName();
            currencySymbol.setText(symbol);
            currencyName.setText(cName);
            currencyCode.setText(code);
            region.setText(strRegion);
            Log.e("CountryDetails", "setViews"+country.getName());
        }

    }

}
