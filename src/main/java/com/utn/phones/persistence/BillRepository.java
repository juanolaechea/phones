package com.utn.phones.persistence;

import com.utn.phones.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {




/*
    List<Bill> findByRank(Integer idClient, LocalDateTime start, LocalDateTime end);

 */
}
