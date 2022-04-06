package com.nhnacademy.payment;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.payment.alarm.Alarm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentServiceResultTest {

    PaymentService paymentService;
    AccountService accountService;
    Coupon coupon;

    @BeforeEach
    void setUp() {
        accountService = mock(AccountService.class);
        paymentService = spy(new PaymentService(accountService));
        coupon = Coupon.FIXED;
    }

    @DisplayName("결제 메세지 전송 확인")
    @Test
    void acceptMsg(){
        Account account = spy(new Account("park",1_000L,100L));
        String id = account.getId();
        Long amount = 1000L;


        when(accountService.login(id)).thenReturn(account);

        paymentService.pay(id, amount, coupon);
        verify(account, times(3))
            .acceptSms(refEq(new Alarm(account, amount)));
    }

    @DisplayName("영수증 발행")
    @Test
    void takeReceipt(){
        Account account = spy(new Account("park",1_000L,100L));
        String id = account.getId();
        long amount = 1000L;

        when(accountService.login(id)).thenReturn(account);

        paymentService.pay(id, amount, coupon);
        verify(account, times(1))
            .takeReceipt(refEq(new Receipt(account, amount)));
    }
}
