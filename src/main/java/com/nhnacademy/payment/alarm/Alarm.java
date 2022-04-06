package com.nhnacademy.payment.alarm;

import com.nhnacademy.payment.Account;

public class Alarm {
    private final Account account;
    private final Long amount;
    private final Long point;

    public Alarm(Account account, Long amount) {
        this.account = account;
        this.amount = amount;
        this.point = (long) (amount * 0.01);
    }

    @Override
    public String toString() {
        String[] alarmType = getClass().toString().split("\\.");
        return String.format("[%s] %s님 %d원이 결제되었습니다. %d원이 적립되었습니다. 총 적립금은 %d원 입니다.",
            alarmType[alarmType.length - 1], account.getId(), amount, point, account.getPoint());
    }
}
