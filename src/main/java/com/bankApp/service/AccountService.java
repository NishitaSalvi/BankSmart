package com.bankApp.service;

import java.util.List;

import com.bankApp.dto.AccountDto;


public interface AccountService {

    AccountDto createAccount(AccountDto accountdto);
    
    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id , double amount);

    AccountDto withDraw(Long id , double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
    
}
