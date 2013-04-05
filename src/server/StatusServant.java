package server;

import filebox.statusOperations;

public class StatusServant implements statusOperations {
  private int code; 
  private String text;
    
  public StatusServant() {
    super();
  }  

  public StatusServant(int code, String text) {
    super();
    this.code = code;
    this.text = text;
  }

  /**
   * Returns 0 - success, 1 -error, 2 - unknown state
   */
  public int code() {
    return code;
  }

  public void code(int newCode) {
    code = newCode;
  }

  public String text() {
    return text;
  }

  public void text(String newText) {
    text = newText;
  }
}