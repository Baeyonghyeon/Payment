package com.nhnacademy.payment;

import com.nhnacademy.payment.exception.InvalidInputException;

public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account getAccount(String id) {
        return repository.findByUserId(id);
    }

    public Account login(String id) {
        Account findAccount;

        if (id == null || (findAccount = getAccount(id)) == null){
            throw new InvalidInputException("null");
        }

        return findAccount;
    }

}