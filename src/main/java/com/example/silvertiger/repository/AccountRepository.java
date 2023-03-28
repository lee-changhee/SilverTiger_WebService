package com.example.silvertiger.repository;

import com.example.silvertiger.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    Optional<Account> findById(String id);


}
