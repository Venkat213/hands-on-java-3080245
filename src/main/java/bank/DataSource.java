package bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSource {
  public static Connection connect() {
    String url = "jdbc:SQLite:resources/bank.db";
    Connection conn = null;
    try {
      conn = java.sql.DriverManager.getConnection(url);
      System.out.println("Connection to SQLite has been established.");
    } catch (java.sql.SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public static Customers getCustomers(String username) throws SQLException{
    String sql = "SELECT * FROM customers WHERE username = ?";
    Customers customers = null;
    try(Connection connect = connect();
        PreparedStatement preparedStatement = connect.prepareStatement(sql)){
      
          preparedStatement.setString(1, username);
          try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(resultSet.next()){
              customers = new Customers(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getInt("account_id")
              );
            }
          }
    }
    return customers;
  }

  public static Accounts getAccounts(String accountid){
    String sql = "SELECT * FROM accounts WHERE id = ?";
    Accounts accounts = null;
    try(Connection connect = connect();
        PreparedStatement preparedStatement = connect.prepareStatement(sql)){
      
          preparedStatement.setString(1, accountid);
          try(ResultSet resultSet = preparedStatement.executeQuery()){
            if(resultSet.next()){
              accounts = new Accounts(
                resultSet.getInt("id"),
                resultSet.getString("type"),
                resultSet.getDouble("balance")
              );
            }
          }
    } catch (SQLException e) {
      System.out.println("Error retrieving account: " + e.getMessage());
    }
    return accounts;
  }

  public static void updateAccountBalance(int accountId, double newBalance) {
    String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
    try (Connection connect = connect();
         PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
      preparedStatement.setDouble(1, newBalance);
      preparedStatement.setInt(2, accountId);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Error updating account balance: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    try {
      Customers customers = getCustomers("clillea8@nasa.gov");
      if (customers != null) {
        System.out.println("Customer found: " + customers.getName());
      } else {
        System.out.println("Customer not found.");
      }
    } catch (SQLException e) {
      System.out.println("Error retrieving customer: " + e.getMessage());
    }

    try {
      Accounts accounts = getAccounts("10385");
      if (accounts != null) {
        System.out.println("Account found: " + accounts.getType() + " with balance " + accounts.getBalance());
      } else {
        System.out.println("Account not found.");
      }
    } catch (Exception e) {
      System.out.println("Error retrieving account: " + e.getMessage());
    }
  }
}
