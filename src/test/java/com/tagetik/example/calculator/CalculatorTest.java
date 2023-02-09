package com.tagetik.example.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

  // sut: system under test
  private Calculator sut;

  @BeforeEach
  void setUp() {
    sut = new Calculator();
  }

  @Test
  void shouldSumElements() {
    int result = sut.sum(10, 5);

    assertEquals(15, result);
  }

  @Test
  void shouldMultiplyElements() {
    int result = sut.multiply(10, 5);

    assertEquals(50, result);
  }

  @Test
  void multiplicationShouldReturnZeroWhenOneFactorIsZero() {
    assertEquals(0, sut.multiply(10, 0));
    assertEquals(0, sut.multiply(0, 10));
  }
}