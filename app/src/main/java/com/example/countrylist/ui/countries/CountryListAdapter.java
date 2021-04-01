package com.example.countrylist.ui.countries;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countrylist.R;
import com.example.countrylist.database.CountryDatabase;
import com.example.countrylist.database.dao.CountryDao;
import com.example.countrylist.database.entities.CountryEntity;
import com.example.countrylist.database.mapper.CountryMapper;
import com.example.countrylist.models.Country;
import com.example.countrylist.utils.ImageLoader;
import com.example.countrylist.utils.ItemClickListener;
import com.example.countrylist.utils.RemoveItemListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder> {

    private List<Country> countries = new ArrayList<>();

    private ItemClickListener<Country> itemClickListener;
    private RemoveItemListener removeItemListener;

    private boolean isFromDb;

    public CountryListAdapter(ItemClickListener<Country> itemClickListener, boolean isFromDb,RemoveItemListener removeItemListener) {
        this.itemClickListener = itemClickListener;
        this.isFromDb = isFromDb;
        this.removeItemListener = removeItemListener;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list_item, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryListViewHolder holder, int position) {
        Country country = countries.get(position);
        holder.onBind(country);


    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryListViewHolder extends RecyclerView.ViewHolder {

        private TextView countryName;
        private TextView countryCurrency;
        private ImageView countryFlag;
        private ImageButton btnFav;
        private CountryMapper mapper = new CountryMapper();

        public CountryListViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.tv_name);
            countryCurrency = itemView.findViewById(R.id.tv_currency);
            countryFlag = itemView.findViewById(R.id.iv_country);
            btnFav = itemView.findViewById(R.id.btn_fav);
        }

        void onBind(final Country country) {
            countryName.setText(country.getName());
            String currency = country.getCurrencySymbol() + country.getCurrencyCode();
            countryCurrency.setText(currency);
            ImageLoader.loadImageUrl(countryFlag, country.getImgUrl(), countryFlag.getContext());
            itemListener(country);
            if (isFromDb) {
                btnFav.setBackgroundResource(R.drawable.remove);
            } else {
                btnFav.setBackgroundResource(R.drawable.star);
            }
            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isFromDb)
                        removeItemListener.removeItem(country);
                    else
                        addCountryToFavourites(country);
                }
            });

        }

        private void itemListener(final Country country) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(country);
                }
            });
        }

        private void addCountryToFavourites(Country country) {
            CountryDao db = CountryDatabase.getInstance(itemView.getContext()).countryDao();
            List<Country> countries = new ArrayList<>();
            for (CountryEntity entity :
                    db.getFavouriteCountries()) {
                Country fromdb = mapper.map(entity);
                countries.add(fromdb);
            }
            if (!countries.contains(country)) {
                CountryEntity newEntity = mapper.mapInverse(country);
                db.addToFavourite(newEntity);
            }
        }


    }


}
