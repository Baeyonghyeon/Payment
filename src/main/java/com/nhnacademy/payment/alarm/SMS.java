package com.nhnacademy.payment.alarm;

import com.nhnacademy.payment.Account;

public class SMS extends Alarm {

    public SMS(Account account, Long amount) {
        super(account, amount);
    }
}
