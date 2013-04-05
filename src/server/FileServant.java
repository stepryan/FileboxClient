package server;

import org.omg.CORBA.Any;

import filebox.fileOperations;

public class FileServant  implements fileOperations {
  private int id;
  private String name;
  private String content;
  private int status; // 0 - private, 1 - public
  private int userid;
  private Any contentBinary;
    
  public FileServant() {
    super();
  }

  public FileServant(int id, String name, String content, int status, int userid, Any contentBinary) {
    super();
    this.id = id;
    this.content = content;
    this.name = name;
    this.status = status;
    this.userid = userid;
    this.contentBinary = contentBinary;
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

@Override
public Any contentbinary() {
	
	return contentBinary;
}

@Override
public void contentbinary(Any newContentbinary) {
	contentBinary = newContentbinary;
	
}
}