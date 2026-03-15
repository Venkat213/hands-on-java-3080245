package bank;

public class Customers {
  private int id;
  private String name;
  private String username;
  private String password;
  private int account_id;
  private boolean authenticated;

  public Customers(int id, String name, String username, String password, int account_id) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.password = password;
    this.account_id = account_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getAccount_id() {
    return account_id;
  }

  public void setAccount_id(int account_id) {
    this.account_id = account_id;
  }

  public boolean isAuthenticated() {
    return authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

  
}
