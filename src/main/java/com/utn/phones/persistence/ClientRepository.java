package com.utn.phones.persistence;

import com.utn.phones.domain.Client;
import com.utn.phones.domain.PhoneLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findByPhoneLine(PhoneLine phoneLine);

    @Query("select (count(c) > 0) from Client c where c.dni = ?1")
    boolean existsByDni(Integer dni);
}
