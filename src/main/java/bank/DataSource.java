package bank;

import java.sql.Connection;

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

  public static void main(String[] args) {
    connect();
  }
}
