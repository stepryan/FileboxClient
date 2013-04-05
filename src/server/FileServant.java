package server;

import filebox.fileOperations;

public class FileServant  implements fileOperations {
  private int id;
  private String name;
  private String content;
  private int status; // 0 - private, 1 - public
  private int userid;
    
  public FileServant() {
    super();
  }

  public FileServant(int id, String name, String content, int status, int userid) {
    super();
    this.id = id;
    this.content = content;
    this.name = name;
    this.status = status;
    this.userid = userid;
  }

  public String content() {
    return content;
  }

  public void content(String newContent) {
    content = newContent;
  }

  public int id() {
    return id;
  }

  public void id(int newId) {
    id = newId;
  }

  public String name() {
    return name;
  }

  public void name(String newName) {
    name = newName;
  }

  public int status() {
    return status;
  }

  public void status(int newStatus) {
    status = newStatus;
  }

  public int userid() {
    return userid;
  }

  public void userid(int newUserid) {
    userid = newUserid;
  }
}