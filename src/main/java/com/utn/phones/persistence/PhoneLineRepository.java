package com.utn.phones.persistence;

import com.utn.phones.domain.PhoneLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneLineRepository extends JpaRepository<PhoneLine,Integer> {


     PhoneLine findByNumberLine(String number);
}
