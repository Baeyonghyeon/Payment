package com.nhnacademy.payment;

import com.nhnacademy.payment.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class AccountServiceTest {

    AccountService accountService;
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        accountService = new AccountService(accountRepository);
    }

    @DisplayName("잘못된 입력값 입력")
    @Test
    void login(){
        String input = null;

        assertThatThrownBy(() -> accountService.login(input))
            .isInstanceOf(InvalidInputException.class)
            .hasMessage("invalid input");

        Mockito.verify(accountRepository, times(0))
               .findByUserId(input);
    }

    @DisplayName("일치하는 아이디 없음")
    @Test
    void accountIdNotInRepository(){
        String id = "jungwoo";
        Long money = 10_000L;
        Long point = 100L;
        Account account = new Account(id, money, point);

        String invalidId = "dongyeol";

        when(accountRepository.findByUserId(id)).thenReturn(account);

        assertThatThrownBy(() -> accountService.login(invalidId))
            .isInstanceOf(InvalidInputException.class)
            .hasMessage("invalid input");
    }

    @DisplayName("로그인 성공")
    @Test
    void loginSuccess() {
        String id = "jungwoo";
        Long money = 10_000L;
        Long point = 100L;
        Account account = new Account(id, money, point);

        when(accountRepository.findByUserId(id)).thenReturn(account);

        assertThat(accountRepository.findByUserId(id)).isEqualTo(account);
    }
}