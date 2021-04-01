package com.example.countrylist.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.countrylist.R;
import com.example.countrylist.database.CountryDatabase;
import com.example.countrylist.utils.BaseFragment;

public class SettingsFragment extends BaseFragment {

    private SettingsViewModel settingsViewModel;
    private AppCompatButton delete;

    public SettingsFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = new SettingsViewModel();
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        delete = view.findViewById(R.id.btn_delete);
        emptyDbOnClick();
    }


    private void emptyDbOnClick(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryDatabase.getInstance(getContext()).countryDao().removeAll();
            }
        });
    }


}
