package com.nhnacademy.payment.exception;

public class LackOfBalanceException extends IllegalStateException {
    public LackOfBalanceException() {
        super("보유한 잔액이 부족합니다.");
    }
}
