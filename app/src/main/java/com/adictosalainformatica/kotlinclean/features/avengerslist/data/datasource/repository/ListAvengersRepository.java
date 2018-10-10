package com.adictosalainformatica.kotlinclean.features.avengerslist.data.datasource.repository;


import com.adictosalainformatica.kotlinclean.features.avengerslist.domain.entities.AvengersModel;

public interface ListAvengersRepository {
    AvengersModel getAvengersList();
}
