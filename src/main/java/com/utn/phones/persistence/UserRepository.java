package com.utn.phones.persistence;


import com.utn.phones.domain.Client;
import com.utn.phones.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<Object> findByUsername(String username);

    @Query("select u from User u where u.idUser = ?1")
    User findByIdUser(Integer idUser);
}
