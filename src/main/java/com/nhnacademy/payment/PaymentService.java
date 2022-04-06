package com.nhnacademy.payment;

import com.nhnacademy.payment.alarm.AppPush;
import com.nhnacademy.payment.alarm.Email;
import com.nhnacademy.payment.alarm.SMS;
import com.nhnacademy.payment.exception.InvalidInputException;
import com.nhnacademy.payment.exception.LackOfBalanceException;
import com.nhnacademy.payment.exception.MinusAmountException;

public class PaymentService {
    AccountService accountService;

    public PaymentService(AccountService accountService) {
        this.accountService = accountService;
    }

    private void sendMessage(Account account, long amount) {
        account.acceptSms(new SMS(account, amount));
        account.acceptSms(new Email(account, amount));
        account.acceptSms(new AppPush(account, amount));
    }

    private void takeReceipt(Account account, long amount) {
        account.takeReceipt(new Receipt(account, amount));
    }

    public void pay(String id, long amount, Coupon coupon) {
        Account account = null;

        try {
            account = accountService.login(id);
        } catch (InvalidInputException e) {

        }

        if (amount < 0) {
            throw new MinusAmountException();
        }

        // 쿠폰 적용
        amount -= discountWithCoupon(coupon, amount);

        if (!account.pay(amount)) {
            throw new LackOfBalanceException();
        }

        raisePoint(account, amount);
        sendMessage(account, amount);
        takeReceipt(account, amount);

    }

    private Long discountWithCoupon(Coupon coupon, Long amount) {
        Long discountAmt = coupon.getDiscountAmt(amount);
        System.out.println(discountAmt + "원이 할인되었습니다.");
        return discountAmt;
    }

    public void raisePoint(Account account, Long amount) {
        account.addPoint(amount);
    }
}
