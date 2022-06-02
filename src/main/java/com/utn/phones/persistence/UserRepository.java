package com.utn.phones.persistence;


import com.utn.phones.domain.Client;
import com.utn.phones.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    Optional<Object> findByUsername(String username);
}
