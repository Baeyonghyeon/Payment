package com.nhnacademy.payment;

public interface AccountRepository {

    void insert(Account account);

    Account findByUserId(String id);
}
