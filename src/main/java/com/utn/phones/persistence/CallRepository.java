package com.utn.phones.persistence;

import com.utn.phones.domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {

    @Query(value = "SELECT * FROM calls " +
            "WHERE invoice = false ", nativeQuery = true)
    ArrayList<Call> findAllNotInvoice();

    @Query(value = "SELECT * FROM calls" +
            "WHERE client_id_client = :idClient" +
            "BETWEEN :start AND :end", nativeQuery = true)
    List<Call> findCallByRank(Integer idClient, LocalDateTime start, LocalDateTime end);


   @Query(value = "SELECT * FROM Calls C " +
           "WHERE C.client_id_client = :idClient " +
           "AND C.date BETWEEN :from AND :to", nativeQuery = true)
    List<Call> findAllByClientBetweenDates(Integer idClient, LocalDate from, LocalDate to);

}


