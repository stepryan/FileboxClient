package server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import filebox.MessageServer;
import filebox.file;
import filebox.fileHelper;
import filebox.filePOATie;
import filebox.listener;
import filebox.listenerHelper;
import filebox.serviceOperations;
import filebox.status;
import filebox.statusHelper;
import filebox.statusPOATie;
import filebox.user;
import filebox.userHelper;
import filebox.userPOATie;

public class ServiceServant implements serviceOperations {
  // users database (key = user id)
  private NavigableMap<Integer, UserServant> users;
  // keeps IDs of logged in users
  private List<Integer> session;
  // files database (key = file id)
  private NavigableMap<Integer, FileServant> files;
  // keeps message of the latest performed operation
  private StatusServant operationStatus;

  private POA rootPOA;
  private Any contentBinary;
  private  ORB orb;
  public ServiceServant() {
    super();
    users = new TreeMap<Integer, UserServant>(); // sorted by key
    operationStatus = new StatusServant(2, "Uninitialized");
    session = new ArrayList<Integer>();
    files = new TreeMap<Integer, FileServant>(); // sorted by key
  }

  public ServiceServant(POA rootPOA) {
    this();
    this.rootPOA = rootPOA;
    
  }

  /*
   * Helper
   */

  /*
   * Retrieves a status of the last performed operations
   */
  public status getStatus() {
    // create a tie
    statusPOATie statusTie = new statusPOATie(operationStatus);

    status retStatus = null;
    try {
      retStatus = statusHelper.narrow(rootPOA.servant_to_reference(statusTie));
    } catch (ServantNotActive e) {
      e.printStackTrace();
    } catch (WrongPolicy e) {
      e.printStackTrace();
    }

    return retStatus;
  }

  /*
   * Users handling
   */

  /*
   * Creates a new user. Returns: generated id
   */
  public int addUser(String username, String password) {
    // Test ----------
    System.out.println("IDs: " + users.keySet());
    // ---------------

    operationStatus = new StatusServant(2, "Uninitialized"); // reset
    System.out.println("In addUser() -> username=" + username); // server log
    
    // check if a user already exists
    Collection<UserServant> existUsers = users.values();
    for (UserServant existUser : existUsers) {
      System.out.println(existUser.username());

      if (existUser.username().equals(username)) {
        operationStatus = new StatusServant(1, "User \'" + username + "\' already exists"); // 1 = error
        return Integer.MIN_VALUE; // unrealistic id
      }
    }

    // increase the largest existing id in DB
    Integer nextId;
    try {
      nextId = users.lastKey() + 1;
    } catch (NoSuchElementException e) {
      nextId = 1;
    }

    users.put(nextId, new UserServant(nextId, username, password));
    operationStatus = new StatusServant(0, "User \'" + username + "\' created");

    return nextId;
  }

  /*
   * Retrieves a user for given id
   */
  public user getUser(int id) {
    operationStatus = new StatusServant(2, "Uninitialized"); // reset
    System.out.println("In getUser() -> id=" + id); // server log
    
 // Check if user is logged in
    if (!isUserLoggedIn(id)) {
      operationStatus = new StatusServant(1, "User with ID \'" + id + "\' is not logged in"); // 1 = error
      return null;
    }
    
    UserServant userServant = users.get(id);

    if (userServant == null) {
      operationStatus = new StatusServant(1, "UserServant is null"); // reset
      return null;
    }

    // create a tie
    userPOATie userTie = new userPOATie(userServant);

    user retUser = null;
    try {
      retUser = userHelper.narrow(rootPOA.servant_to_reference(userTie));
    } catch (ServantNotActive e) {
      e.printStackTrace();
    } catch (WrongPolicy e) {
      e.printStackTrace();
    }

    operationStatus = new StatusServant(0, "User \'" + userServant.username() + "\' returned.");
    return retUser;
  }

  /*
   * Authenticates a user. Returns: user's id
   */
  public int login(String username, String password) {
    operationStatus = new StatusServant(2, "Uninitialized"); // reset
    
    // check if a username and password match
    Collection<UserServant> existUsers = users.values();

    for (UserServant existUser : existUsers) {
      System.out.println(existUser.username() + ", " + existUser.password());

      if (existUser.username().equals(username) && existUser.password().equals(password)) {
        operationStatus = new StatusServant(0, "User \'" + username + "\' logged in");
        session.add(existUser.id());
        System.out.println("Number of logged in users: " + session.size()); // server log

        return existUser.id();
      }
    }

    operationStatus = new StatusServant(1, "Username " + username + " and provided password does not match"); // 1 =
                                                                                                              // error
    return Integer.MIN_VALUE; // unrealistic id
  }
  
  /*
   * File handling
   */

  /*
   * Add a file. Returns: generated id
   */
  public int addFile(String name, String content, int status, int userid) {
    operationStatus = new StatusServant(2, "Uninitialized"); // reset
    
    // Check if user is logged in
    if (!isUserLoggedIn(userid)) {
      operationStatus = new StatusServant(1, "User with ID \'" + userid + "\' is not logged in"); // 1 = error
      return Integer.MIN_VALUE; // unrealistic id
    }

    // check if a file already exists for a given user
    Collection<FileServant> existFiles = files.values();

    for (FileServant existFile : existFiles) {
      System.out.println(existFile.name());

      if (existFile.name().equals(name) && existFile.userid() == userid) {
        operationStatus = new StatusServant(1, "File " + name + " already exists"); // 1 = error
        return Integer.MIN_VALUE; // unrealistic id
      }
    }

    // increase the largest existing id in DB
    Integer nextId;
    try {
      nextId = files.lastKey() + 1;
    } catch (NoSuchElementException e) {
      nextId = 1;
    }

    // TODO: contentBinary added but should be revised, this is not a good way (should replace content)
    files.put(nextId, new FileServant(nextId, name, content, status, userid, contentBinary));
    operationStatus = new StatusServant(0, "File " + name + " added");

    return nextId;
  }

  /**
   * 
   * @param name
   *          Name of a file to be retrieved
   * @param userid
   *          User identifier
   * @return File
   */
  public file getFile(String name, int userid) {
    operationStatus = new StatusServant(2, "Uninitialized"); // reset
    
    // Check if user is logged in
    if (!isUserLoggedIn(userid)) {
      operationStatus = new StatusServant(1, "User with ID \'" + userid + "\' is not logged in"); // 1 = error
      return null;
    }

    Collection<FileServant> existFiles = files.values();

    for (FileServant existFile : existFiles) {
      if (existFile.name().equals(name) && existFile.userid() == userid) {
        operationStatus = new StatusServant(0, "File named \'" + name + "\' found");

        // create a tie
        filePOATie fileTie = new filePOATie(existFile);

        file retFile = null;
        try {
          retFile = fileHelper.narrow(rootPOA.servant_to_reference(fileTie));
        } catch (ServantNotActive e) {
          e.printStackTrace();
        } catch (WrongPolicy e) {
          e.printStackTrace();
        }

        return retFile;
      }
    }

    operationStatus = new StatusServant(0, "File " + name + " for user ID \'" + userid + "\' does not exist");
    return null;
  }

  /**
   * Returns all files for a particular user
   * 
   * @param userid
   *          User identifier
   */
  public file[] getFiles(int userid) {
    operationStatus = new StatusServant(2, "Uninitialized"); // reset
    
    // Check if user is logged in
    if (!isUserLoggedIn(userid)) {
      operationStatus = new StatusServant(1, "User with ID \'" + userid + "\' is not logged in"); // 1 = error
      return null;
    }

    List<file> filesForUser = new ArrayList<file>();    
    Collection<FileServant> existFiles = files.values();
    for(FileServant existFile : existFiles) {
      if(existFile.userid() == userid) {
        // create a tie
        filePOATie fileTie = new filePOATie(existFile);

        file retFile = null;
        try {
          retFile = fileHelper.narrow(rootPOA.servant_to_reference(fileTie));
        } catch (ServantNotActive e) {
          e.printStackTrace();
        } catch (WrongPolicy e) {
          e.printStackTrace();
        }
        filesForUser.add(retFile);
      }
    }      
    operationStatus = new StatusServant(0, "Number of files for user ID \'" + userid + "\': " + filesForUser.size());

    file[] retFiles = new file[filesForUser.size()];
    retFiles = filesForUser.toArray(retFiles);
    
    return retFiles;
  }

  public file changeFileStatus(String filelist, short fileid, short filestatus) {
    // TODO Auto-generated method stub
    return null;
  }

  public file fileUpdate(String filelist, int lastchanged, short fileid, listener callback) {
    // TODO Auto-generated method stub
    return null;
  }

  public void register(listener lt) {
    
  }

  public void removeFile(short fileid) {
    // TODO Auto-generated method stub
  }

  private boolean isUserLoggedIn(int userId) {
    // TODO: Check if user is logged in
    boolean loggedIn = false;

    for (int id : session) {
      if (id == userId) {
        loggedIn = true;
        break;
      }
    }

    return loggedIn;
  }

  public file addFileBinary(Any contentbinary, String filename, short fileid, short filestatus) {
    // TODO Auto-generated method stub
    return null;
  }

public void setORB(ORB orb_val) {
	orb = orb_val;
	
}
}