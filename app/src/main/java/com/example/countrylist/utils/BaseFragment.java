package com.example.countrylist.utils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.countrylist.R;

    public class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    public BaseFragment() {
        // Required empty public constructor
    }

    protected NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            navController = NavHostFragment.findNavController(this);
        } catch (Exception e) {
            Log.e(TAG, "does not have a NavController set");
        }
    }
}