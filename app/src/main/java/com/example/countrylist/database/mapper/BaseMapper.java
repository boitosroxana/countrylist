package com.example.countrylist.database.mapper;

public interface BaseMapper<E, M> {

    public M map(E entity);

    public E mapInverse(M model);
}
