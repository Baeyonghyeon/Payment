package com.nhnacademy.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.payment.alarm.SMS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentServicePointTest {

    PaymentService paymentService;
    AccountService accountService;
    Coupon coupon;

    @BeforeEach
    void setUp() {
        accountService = mock(AccountService.class);
        paymentService = spy(new PaymentService(accountService));
        coupon = Coupon.FIXED;
    }

    @DisplayName("포인트 적립 확인")
    @Test
    void raisePoint() throws NoSuchMethodException {
        Account account = new Account("park", 1_000L, 100L);
        String id = account.getId();
        Long amount = 1000L;
        coupon = Coupon.RATE;

        when(accountService.login(id)).thenReturn(account);

        paymentService.pay(id, amount, coupon);
        verify(paymentService, times(1)).raisePoint(account, amount);
    }

    @DisplayName("고객에게 포인트 적립되었나 확인")
    @Test
    void isSaved(){
        Account account = spy(new Account("park",1_000L,100L));
        String id = account.getId();
        Long amount = 1000L;
        Long beforeRaisePoint = account.getPoint();
        coupon = Coupon.RATE;

        when(accountService.login(id)).thenReturn(account);

        paymentService.pay(id, amount, coupon);

        Long afterRaisePoint = account.getPoint();

        assertThat(afterRaisePoint).isEqualTo((long) (beforeRaisePoint + (amount * 0.01)));
        verify(account, times(1)).addPoint(amount);
    }

    @DisplayName("결제 메세지 전송 확인")
    @Test
    void acceptMsg(){
        Account account = spy(new Account("park",1_000L,100L));
        String id = account.getId();
        Long amount = 1000L;
        coupon = Coupon.RATE;

        when(accountService.login(id)).thenReturn(account);

        paymentService.pay(id, amount, coupon);
        verify(account, times(1))
            .acceptSms(refEq(new SMS(account, amount)));
    }

    @DisplayName("영수증 발행")
    @Test
    void takeReceipt(){
        Account account = spy(new Account("park",1_000L,100L));
        String id = account.getId();
        Long amount = 1000L;
        coupon = Coupon.RATE;

        when(accountService.login(id)).thenReturn(account);

        paymentService.pay(id, amount, coupon);
        verify(account, times(1))
            .takeReceipt(refEq(new Receipt(account, amount)));
    }
}
