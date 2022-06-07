package com.utn.phones.persistence;

import com.utn.phones.domain.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {

    @Query(value ="SELECT * FROM calls "+
            "WHERE invoice = false ",nativeQuery = true)
    ArrayList<Call> findAllNotInvoice();
}
