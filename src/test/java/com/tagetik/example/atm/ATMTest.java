package com.tagetik.example.atm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.tagetik.example.atm.impl.ATMImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ATMTest {

  private ATM sut;

  @BeforeEach
  void setUp() {
    sut = new ATMImpl();
  }

  @Test
  void shoudlThrowExceptionWhenWithdrawAmountIsGratherThanCurrentBalance() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sut.withdraw(10.0));

    assertEquals("The withdrawal is greater than the balance", exception.getMessage());
  }

  @Test
  @Disabled("Show coverage IDE integration")
  void shoudlThrowExceptionWhenWithdrawAmountIsNegative() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sut.withdraw(-10.0));

    assertEquals("Only positive amount", exception.getMessage());
  }

  @Test
  @Disabled("Show coverage IDE integration")
  void shoudlThrowExceptionWhenWithdrawAmountIsZero() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sut.withdraw(0));

    assertEquals("Only positive amount", exception.getMessage());
  }

  @Test
  void shuldReturnBalanceAfterWithdraw() {
    sut.deposit(10);

    double balance = sut.withdraw(5);

    assertEquals(5.0, balance, 0.001);
  }

  @Test
  void shouldThrowExcetpionWhenDepositAmountIsNegative() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sut.deposit(-10));

    assertEquals("Only positive amount", exception.getMessage());
  }

  @Test
  void shouldThrowExcetpionWhenDepositAmountIsZero() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sut.deposit(0));

    assertEquals("Only positive amount", exception.getMessage());
  }

  @Test
  void shouldDepositAmountToBalance() {
    assertEquals(10, sut.deposit(10));
    assertEquals(15, sut.deposit(5));
  }

  @Test
  void shouldRetrieveAccountBalance() {
    assertEquals(0.0, sut.getBalance());
  }

  @Test
  void shouldRetriveAccountBalanceAfterDepositAndWithdraw() {
    sut.deposit(10.0);
    sut.withdraw(5.0);

    assertEquals(5.0, sut.getBalance());
  }
}