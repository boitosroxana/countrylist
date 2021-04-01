package com.example.countrylist.database.mapper;

import com.example.countrylist.database.entities.CountryEntity;
import com.example.countrylist.models.Country;

public class CountryMapper implements BaseMapper<CountryEntity, Country> {
    @Override
    public Country map(CountryEntity entity) {
        return new Country(
                entity.getId(),
                entity.getName(),
                entity.getImgUrl(),
                entity.getCurrencyCode(),
                entity.getCurrencySymbol(),
                entity.getRegion(),
                entity.getCurrencyName()
        );
    }

    @Override
    public CountryEntity mapInverse(Country model) {
        return new CountryEntity(
                model.getId(),
                model.getName(),
                model.getImgUrl(),
                model.getCurrencyCode(),
                model.getCurrencySymbol(),
                model.getRegion(),
                model.getCurrencyName()
        );
    }
}
