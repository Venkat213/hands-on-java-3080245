package bank;

import bank.exceptions.AmountException;

public class Accounts {
  private int id;
  private String type;
  private double balance;

  public Accounts(int id, String type, double balance) {
    this.id = id;
    this.type = type;
    this.balance = balance;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  void deposit(double amount) throws AmountException {
    if(amount < 1) {
      throw new AmountException("Deposit amount must be minimum $1.");

    } else {
      double newBalance = balance + amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    }
  }

  void withdraw(double amount) throws AmountException {
    if (amount > 0 && balance >= amount) {
      double newBalance = balance - amount;
      setBalance(newBalance);
      DataSource.updateAccountBalance(id, newBalance);
    } else {
      throw new AmountException("Insufficient funds or invalid withdrawal amount.");
    }
  }
}
