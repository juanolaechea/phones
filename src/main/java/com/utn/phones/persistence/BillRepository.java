package com.utn.phones.persistence;

import com.utn.phones.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value = "SELECT B FROM bills"+
                   "WHERE B.client_id_client = :idClient"+
                     "BETWEEN :start AND :end",nativeQuery = true)
    List<Bill> findBillByRank(Integer idClient, LocalDateTime start, LocalDateTime end);


}
