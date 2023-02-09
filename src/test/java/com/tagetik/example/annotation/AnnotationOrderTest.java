package com.tagetik.example.annotation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AnnotationOrderTest {

  @BeforeAll
  public static void beforeAll() {
    System.out.println("BeforeAll");
  }

  @AfterAll
  public static void afterAll() {
    System.out.println("AfterAll");
  }

  @BeforeEach
  public void setUp() {
    System.out.println("BeforeEach");
  }

  @AfterEach
  public void after() {
    System.out.println("AfterEach\n");
  }

  @Test
  void activeTest1() {
    System.out.println("activeTest1");
  }

  @Test
  void activeTest2() {
    System.out.println("activeTest2");
  }

  @Test
  @Disabled("OtherTest doesn't work yet")
  void otherTest() {
    System.out.println("otherTest");
  }
}