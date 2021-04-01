package com.example.countrylist.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrylist.R;
import com.example.countrylist.database.CountryDatabase;
import com.example.countrylist.database.dao.CountryDao;
import com.example.countrylist.database.entities.CountryEntity;
import com.example.countrylist.database.mapper.CountryMapper;
import com.example.countrylist.models.Country;
import com.example.countrylist.ui.countries.CountryListAdapter;
import com.example.countrylist.utils.BaseFragment;
import com.example.countrylist.utils.ItemClickListener;
import com.example.countrylist.utils.RemoveItemListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private HomeViewModel homeViewModel;
    private TextView emptyList;
    private CountryMapper mapper = new CountryMapper();
    private List<Country> tempCountries = new ArrayList<>();

    private CountryListAdapter adapter = new CountryListAdapter(new ItemClickListener<Country>() {
        @Override
        public void onItemClick(Country item) {
            Bundle args = new Bundle();
            String szCountry = new Gson().toJson(item);
            args.putString("country", szCountry);
            navController.navigate(R.id.navigation_details, args);
        }
    }, true, new RemoveItemListener() {
        @Override
        public void removeItem(Country item) {
            removeCountryFromFavourites(item);
        }
    });
    private RecyclerView rvCountries;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new HomeViewModel();
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //CountryDatabase.getInstance(getContext()).countryDao().removeAll();
        setupObservers();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCountries=view.findViewById((R.id.rv_countries));
        emptyList = view.findViewById(R.id.tv_empty_list);
        setRecyclerView();

    }

    private void setupObservers(){
        homeViewModel.getCountries(getContext()).observe(getViewLifecycleOwner(), new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                if (!countries.isEmpty()) {
                    tempCountries = countries;
                    rvCountries.setVisibility(View.VISIBLE);
                    emptyList.setVisibility(View.GONE);
                    adapter.setCountries(tempCountries);
                    adapter.notifyDataSetChanged();
                } else{
                    rvCountries.setVisibility(View.GONE);
                    emptyList.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setRecyclerView(){
        LinearLayoutManager llm= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvCountries.setLayoutManager(llm);
        rvCountries.setAdapter(adapter);

    }

    private void removeCountryFromFavourites(Country country) {
        tempCountries.remove(country);
        adapter.notifyDataSetChanged();
        CountryDao db = CountryDatabase.getInstance(getContext()).countryDao();
        CountryEntity entity = mapper.mapInverse(country);
        db.deleteFromFavourites(entity);
    }
}
