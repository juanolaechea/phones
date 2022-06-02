package com.utn.phones.persistence;


import com.utn.phones.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {


    City findByCode(String code);


    @Query(value ="SELECT * FROM cities C "+
            "WHERE :number LIKE CONCAT( C.code, '%') AND "+
            "length (C.code) = (SELECT MAX(length(C.code)) "+
            "FROM cities C "+
            "WHERE :number LIKE CONCAT(C.code, '%'))",nativeQuery = true)
    City getCodeByNumber(String number);




}
