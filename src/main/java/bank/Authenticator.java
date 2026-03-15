package bank;

import java.sql.SQLException;

public class Authenticator {

  public static Customers login(String username, String password) throws SQLException {
    Customers customers = DataSource.getCustomers(username);
    if (customers != null && customers.getPassword().equals(password)) {
      customers.setAuthenticated(true);
      return customers;
    }
    return null;
  }

  public static void logout(Customers customers) {
    if (customers != null) {
      customers.setAuthenticated(false);
    }
  }  

}