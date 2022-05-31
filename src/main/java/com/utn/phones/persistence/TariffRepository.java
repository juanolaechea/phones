package com.utn.phones.persistence;

import com.utn.phones.domain.City;
import com.utn.phones.domain.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends JpaRepository<Tariff,Integer> {


    Tariff findByCityOriginAndCityDestination(City cityOrigin, City cityDestination);
}
