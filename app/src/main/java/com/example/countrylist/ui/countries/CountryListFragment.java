package com.example.countrylist.ui.countries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrylist.R;
import com.example.countrylist.models.Country;
import com.example.countrylist.utils.BaseFragment;
import com.example.countrylist.utils.ItemClickListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CountryListFragment extends BaseFragment {

    private CountryListViewModel countryListViewModel;

    private CountryListAdapter adapter = new CountryListAdapter(new ItemClickListener<Country>() {
        @Override
        public void onItemClick(Country item) {
            Bundle args = new Bundle();
            String szCountry = new Gson().toJson(item);
            args.putString("country", szCountry);
            navController.navigate(R.id.navigation_details, args);
        }
    });
    private RecyclerView rvCountries;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        countryListViewModel = new CountryListViewModel();

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        setupObservers();

        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCountries=view.findViewById((R.id.rv_countries));
        setRecyclerView();

    }

    private void setupObservers(){
        countryListViewModel.getCountries().observe(getViewLifecycleOwner(), new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
               adapter.setCountries(countries);
                adapter.notifyDataSetChanged();


            }
        });
     }

     private void setRecyclerView(){
        LinearLayoutManager llm= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvCountries.setLayoutManager(llm);
        rvCountries.setAdapter(adapter);

    }
}
