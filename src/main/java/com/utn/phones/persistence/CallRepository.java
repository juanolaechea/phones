package com.utn.phones.persistence;

import com.utn.phones.domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {
}
