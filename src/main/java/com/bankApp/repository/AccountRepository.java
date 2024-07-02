package com.bankApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankApp.entity.Account;

public interface AccountRepository extends JpaRepository<Account , Long> {

}
