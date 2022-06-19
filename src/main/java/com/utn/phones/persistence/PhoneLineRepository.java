package com.utn.phones.persistence;

import com.utn.phones.domain.PhoneLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneLineRepository extends JpaRepository<PhoneLine,Integer> {



     PhoneLine findByNumberLine(String number);


     @Query("select (count(p) > 0) from PhoneLine p where p.numberLine = ?1")
     Boolean existsByNumberLine(String number);
}
