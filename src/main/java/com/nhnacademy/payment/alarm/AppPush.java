package com.nhnacademy.payment.alarm;

import com.nhnacademy.payment.Account;

public class AppPush extends Alarm{

    public AppPush(Account account, Long amount) {
        super(account, amount);
    }
}
