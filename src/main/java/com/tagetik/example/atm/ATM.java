package com.tagetik.example.atm;

public interface ATM {

  /**
   * Adds money to the account balance.
   *
   * @param amount of money to be deposited (must be > 0)
   * @return account balance
   * @throws IllegalArgumentException if requested deposit is <= 0
   */
  double deposit(double amount);

  /**
   * Withdraw requested amount from user balance.
   *
   * @param amount of money to withdraw
   * @return account balance
   * @throws IllegalArgumentException if request withdraw is > current balance.
   */
  double withdraw(double amount);

  /**
   * @return amount of money in a user account. The balance is always >= 0.
   */
  double getBalance();
}
