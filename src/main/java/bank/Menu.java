package bank;

import java.util.Scanner;

import bank.exceptions.AmountException;

public class Menu {

  private Scanner scanner;

  public static void main(String[] args) {
    System.out.println("Welcome to the Bank Application!");
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);
    System.out.print("Enter your username: ");
    String username = menu.scanner.nextLine();
    System.out.print("Enter your password: ");
    String password = menu.scanner.nextLine();
    Customers customers = menu.authenticateUser(username, password);
    if (customers != null) {
      System.out.println("Authentication successful!");
      Accounts accounts = DataSource.getAccounts(String.valueOf(customers.getAccount_id()));
      menu.showMainMenu(customers, accounts);
    } else {
      System.out.println("Authentication failed. Please check your credentials.");
    }
    menu.scanner.close();
  }

  private Customers authenticateUser(String username, String password) {
    Customers customers = null;
    try {
      customers = Authenticator.login(username, password);
    } catch (Exception e) {
      System.out.println("Authentication failed: " + e.getMessage());
    }
    return customers;
  }

  private void showMainMenu(Customers customers, Accounts accounts) {
    while (true) {
      System.out.println("Main Menu:");
      System.out.println("1. View Account Balance");
      System.out.println("2. Deposit Funds");
      System.out.println("3. Withdraw Funds");
      System.out.println("4. Exit");
      System.out.print("Please select an option: ");
      int choice = scanner.nextInt();
      switch (choice) {
        case 1:
          System.out.println("Viewing account balance..." + accounts.getBalance());
          break;
        case 2:
          System.out.println("Depositing funds...");
          double amount = scanner.nextDouble();
          try {
            accounts.deposit(amount);
          } catch (AmountException e) {
            e.printStackTrace();
          }
          break;
        case 3:
          System.out.println("Withdrawing funds...");
          double withdrawAmount = scanner.nextDouble();
          try {
            accounts.withdraw(withdrawAmount);
          } catch (AmountException e) {
            e.printStackTrace();
          }
          break;
        case 4:
          System.out.println("Exiting...");
          Authenticator.logout(customers);
          return;
        default:
          System.out.println("Invalid option. Please try again.");
      }

    }
  }

}
