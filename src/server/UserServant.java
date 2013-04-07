package server;

import filebox.userOperations;

public class UserServant implements userOperations {
  private int id;
  private String username;
  private String password;
  private long lastlogin;

  public UserServant() {
    super();
  }

  public UserServant(int id, String username, String password) {
    super();
    
    this.id = id;
    this.username = username;
    this.password = password;
  }

  public int id() {
    return id;
  }
  public int lastlogin() {
    return lastlogin();
  }
  public void lastlogin(int newLastlogin) {
    this.lastlogin = newLastlogin;    
  }  
  public String password() {
    return password;
  }
  public void password(String newPassword) {
    this.password = newPassword;
  }
  public String username() {
    return username;
  }
  public void username(String newUsername) {
    this.username = newUsername;
  }

  @Override
  public String toString() {
    return "User[id=" + id + ", username=" + username + ", password=" + password + "]";
  }    
}