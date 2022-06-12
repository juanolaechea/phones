package com.utn.phones.persistence;

import com.utn.phones.domain.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Integer> {



    @Query(value = "SELECT * FROM bands B "  +
            "WHERE B.fk_tariff = :idTariff",nativeQuery = true)
    List<Band> findAllByTariff(Integer idTariff);
}
