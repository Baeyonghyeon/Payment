package com.nhnacademy.payment;

public class Receipt {

    private final Account account;
    private final Long amount;
    private final Long point;

    public Receipt(Account account, Long amount) {
        this.account = account;
        this.amount = amount;
        this.point = (long)(amount * 0.1);
    }

    @Override
    public String toString() {
        return "=== 영수증 ===" + "\n"
            + "ID: " + account.getId() + "\n"
            + "결제금액: " + amount + "\n"
            + "적립금: " + point + "\n"
            + "총 적립금:" + account.getPoint() + "\n";
    }
}
