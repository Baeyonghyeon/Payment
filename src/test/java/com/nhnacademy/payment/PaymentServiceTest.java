package com.nhnacademy.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.payment.exception.LackOfBalanceException;
import com.nhnacademy.payment.exception.MinusAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

    PaymentService paymentService;
    AccountService accountService;
    Account account;
    Coupon coupon;

    @BeforeEach
    void setUp() {
        accountService = mock(AccountService.class);
        paymentService = new PaymentService(accountService);
        account = new Account("park", 1_000L, 100L);
        coupon = Coupon.RATE;
    }

    @DisplayName("상품 가격이 0 미만일 때")
    @Test
    void minusAmount() {
        String id = account.getId();
        long amount = -1000L;

        when(accountService.login(id)).thenReturn(account);

        assertThatThrownBy(() -> paymentService.pay(id, amount, coupon))
            .isInstanceOf(MinusAmountException.class)
            .hasMessage("가격이 음수에요");
    }

    @DisplayName("고객 잔액 부족")
    @Test
    void failAccountPay() {
        String id = account.getId();
        long amount = 2_000L;

        when(accountService.login(id)).thenReturn(account);

        assertThatThrownBy(() -> paymentService.pay(id, amount, coupon))
            .isInstanceOf(LackOfBalanceException.class)
            .hasMessage("보유한 잔액이 부족합니다.");
    }

    @DisplayName("고객 포인트로만 결제")
    @Test
    void accountPay() {
        String id = account.getId();
        long amount = 100L;

        when(accountService.login(id)).thenReturn(account);

        Long beforePayMoney = account.getMoney();

        paymentService.pay(id, amount, coupon);
        assertThat(account.getMoney()).isEqualTo(beforePayMoney);
    }

}