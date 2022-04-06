package com.nhnacademy.payment;

import com.nhnacademy.payment.alarm.Alarm;

public class Account {

    private final static double POINT_RATE = 0.01;

    private final String id;
    private Long money;
    private Long point;

    public Account(String id, Long money, Long point) {
        this.id = id;
        this.money = money;
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public Long getPoint() {
        return point;
    }

    public void addPoint(Long amount) {
        this.point += (long) (amount * POINT_RATE);
    }

    public void acceptSms(Alarm sms) {
        System.out.println(sms);
    }

    public void takeReceipt(Receipt receipt) {
        System.out.println(receipt);
    }

    public boolean pay(long amount) {
        if (amount > (point + money)) {
            // 적립금 + 잔고가 상품가격보다 작을 때
            return false;
        }

        if (point >= amount) {
            // 포인트로만 계산이 가능할 때
            point -= amount;
            return true;
        }

        this.money += this.point;
        this.point = 0L;

        this.money -= amount;

        return true;
    }

    public Long getMoney() {
        return this.money;
    }
}