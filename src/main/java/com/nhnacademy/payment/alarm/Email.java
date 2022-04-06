package com.nhnacademy.payment.alarm;

import com.nhnacademy.payment.Account;

public class Email extends Alarm {

    public Email(Account account, Long amount) {
        super(account, amount);
    }
}
