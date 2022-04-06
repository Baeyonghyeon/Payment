package com.nhnacademy.payment;

public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account getAccount(String id) {
        return repository.findByUserId(id); //해쉬맵에있는데 인터페이스의 함수를 호출하면 상속받은 함수가 실행됨??
    }

    public Account login(String id) {
        if (id == null){
            throw new IllegalArgumentException("null");
        }
        return getAccount(id);
    }

}