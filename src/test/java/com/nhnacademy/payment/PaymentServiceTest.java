package com.nhnacademy.payment;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    PaymentService paymentService;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        paymentService = new PaymentService();

    }

    @Test
    void InputIsNull(){
        String input = null;
        assertThatThrownBy(() -> paymentService.pay(input))
                .isInstanceOf(E.class)
    }
    @Test
    void accountIdNotInRepository(){

    }
}