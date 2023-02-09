package com.tagetik.example.atm.impl;

import com.tagetik.example.atm.ATM;

public class ATMImpl implements ATM {

  private double balance;

  public ATMImpl() {
    balance = 0.0;
  }

  @Override
  public double deposit(double amount) {
    if (amount <= 0.0) {
      throw new IllegalArgumentException("Only positive amount");
    }
    balance += amount;
    return balance;
  }

  @Override
  public double withdraw(double amount) {
    if (amount <= 0.0) {
      throw new IllegalArgumentException("Only positive amount");
    }
    if (amount > balance) {
      throw new IllegalArgumentException("The withdrawal is greater than the balance");
    }
    balance -= amount;
    return balance;
  }

  @Override
  public double getBalance() {
    return balance;
  }
}
