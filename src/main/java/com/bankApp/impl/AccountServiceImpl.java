package com.bankApp.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bankApp.dto.AccountDto;
import com.bankApp.entity.Account;
import com.bankApp.mapper.AccountMapper;
import com.bankApp.repository.AccountRepository;
import com.bankApp.service.AccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountdto) {

        Account account = AccountMapper.mapToAccount(accountdto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }


    @Override
    public AccountDto getAccountById(Long id) {
        
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        return AccountMapper.mapToAccountDto(account);

    }


    @Override
    public AccountDto deposit(Long id, double amount) {
       
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);

    }


    @Override
    public AccountDto withDraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
    
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");
        }
        
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    
    }


    @Override
    public List<AccountDto> getAllAccounts() {
        
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }


    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
    
        accountRepository.deleteById(id);
        

    }

}
