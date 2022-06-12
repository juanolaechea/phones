package com.utn.phones.persistence;

import com.utn.phones.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value = "SELECT * FROM Bills B "+
                   "WHERE B.client_id_client = :idClient "+
                     "AND B.date BETWEEN :from AND :to",nativeQuery = true)
    List<Bill> findAllByClientBetweenDates(Integer idClient, LocalDate from, LocalDate to);


}
