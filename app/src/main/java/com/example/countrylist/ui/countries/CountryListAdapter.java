package com.example.countrylist.ui.countries;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrylist.R;
import com.example.countrylist.models.Country;
import com.example.countrylist.utils.ImageLoader;
import com.example.countrylist.utils.ItemClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder> {

    private List<Country> countries= new ArrayList<>();

    private ItemClickListener<Country> itemClickListener;

    public CountryListAdapter(ItemClickListener<Country> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item,parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {
        Country country= countries.get(position);
        holder.onBind(country);


    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryListViewHolder extends RecyclerView.ViewHolder{

        private TextView countryName;
        private TextView countryCurrency;
        private ImageView countryFlag;

        public CountryListViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName= itemView.findViewById(R.id.tv_name);
            countryCurrency= itemView.findViewById(R.id.tv_currency);
            countryFlag=itemView.findViewById(R.id.iv_country);
        }

        void onBind(Country country){
            countryName.setText(country.getName());
            String cureency= country.getCurrencySymbol()+country.getCurrencyCode();
            countryCurrency.setText(cureency);
            ImageLoader.loadImageUrl(countryFlag,country.getImgUrl(), countryFlag.getContext());
            itemListener(country);

        }
        private void itemListener(final Country country) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(country);
                }
            });
        }

    }


}
