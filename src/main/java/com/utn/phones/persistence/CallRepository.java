package com.utn.phones.persistence;

import com.utn.phones.domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {

    @Query(value ="SELECT * FROM calls "+
            "WHERE invoice = false ",nativeQuery = true)
    ArrayList<Call> findAllNotInvoice();

    @Query(value = "SELECT * FROM calls"+
            "WHERE client_id_client = :idClient"+
            "BETWEEN :start AND :end",nativeQuery = true)
    List<Call> findCallByRank(Integer idClient, LocalDateTime start, LocalDateTime end);


    @Query(value = "SELECT * from calls c " +
            "inner join clients cl on cl.id_client = c.client_id_client " +
            "where cl.id_client = :idClient " +
            " between date :from and date :to ", nativeQuery = true)
   List<Call> findAllByClientBetween(Integer idClient, LocalDateTime from, LocalDateTime to);
}
