package filebox.client;

import filebox.listener;
import server.ListenerImpl;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import filebox.MessageServer;
import filebox.MessageServerHelper;
import filebox.listenerHelper;
import filebox.service;
import filebox.serviceHelper;
import filebox.status;
import filebox.user;

public class Client {
  private service fileboxService;
  private int userId; // keep id for further communication with the server

  public static void main(String[] args) {
    // a command is mandatory
    if (args.length < 1) {
      System.out.println("Usage: java -jar fbclient.jar <ARGUMENTS>");
      System.exit(0);
    }

    Client c = new Client();

    c.init();

    // business methods
    if ("adduser".equalsIgnoreCase(args[0])) {
      c.addUser(args[1], args[2]);
    } else if ("getuser".equalsIgnoreCase(args[0])) {
      c.getUser(Integer.parseInt(args[1]));
    } else if ("login".equalsIgnoreCase(args[0])) {
      c.login(args[1], args[2]);
    } else if ("addfile".equalsIgnoreCase(args[0])) {
      c.addFile(args[1], args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[5]));
    } else if ("printstatus".equalsIgnoreCase(args[0])) {
      c.getStatus(true);
    }
  }

  private void init() {
    try {
      ORB orb = ORB.init(new String[0], null);
      POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      ListenerImpl listen = new ListenerImpl();
      rootPOA.activate_object(listen);
      listener reference = listenerHelper.narrow(rootPOA.servant_to_reference(listen));
      MessageServer mServer = MessageServerHelper.narrow(orb.string_to_object("corbaname:iiop:1.2:1050#MessageServer"));
      mServer.register(reference);
      System.out.println("Registered with Message Server");
      rootPOA.the_POAManager().activate();
      orb.run();
      // BufferedReader br = new BufferedReader(new FileReader("filebox.ior"));
      // TODO: Use naming service instead?
      BufferedReader br = new BufferedReader(new FileReader("/home/dejan/git/Filebox/filebox.ior"));
      String ior = br.readLine();
      org.omg.CORBA.Object obj = orb.string_to_object(ior);

      fileboxService = serviceHelper.narrow(obj);

      if (fileboxService == null) {
        System.out.println("ERROR: Some problem with ORB.");
        System.exit(0);
      }
    } catch (Exception e) {
      System.out.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
  }

  private void addUser(String username, String password) {
    userId = fileboxService.addUser(username, password);

    status currentStatus = getStatus(true);
    if (currentStatus.code() == 0)
      System.out.println("Generated user with ID: " + userId);
  }

  private void getUser(int userId) {
    user userObj = fileboxService.getUser(userId);

    status currentStatus = getStatus(true);
    if (currentStatus.code() == 0)
      System.out.println("Retrieved details for ID \'" + userId + "\': " + "[id=" + userObj.id() + ", username="
          + userObj.username() + ", password=" + userObj.password() + "]");
  }

  private void login(String username, String password) {
    userId = fileboxService.login(username, password);

    status currentStatus = getStatus(true);
    if (currentStatus.code() == 0)
      System.out.println("Logged in user with ID: " + userId);
  }

  private void addFile(String fullName, String title, int type, int status, int userId) {
    String content = null;
    if (type == 1) { // text
      content = readTextFile(fullName);
    } else if (type == 2) { // binary
      content = readBinaryFile(fullName);
    }

    int fileId = fileboxService.addFile(title, content, 1, userId);

    status currentStatus = getStatus(true);
    if (currentStatus.code() == 0)
      System.out.println("Generated file ID: " + fileId);
  }

  private status getStatus(boolean withPrint) {
    status currentStatus = fileboxService.getStatus();
    if (withPrint)
      printStatus(currentStatus);

    return currentStatus;
  }

  private void printStatus(status currentStatus) {
    System.out.println("Status >>> code=" + currentStatus.code() + ", text=" + currentStatus.text());
  }

  private static String readTextFile(String name) {
    StringBuilder sb = new StringBuilder();

    BufferedReader br = null;
    try {
      String line = null;
      br = new BufferedReader(new FileReader(name));

      while ((line = br.readLine()) != null) {
        System.out.println(line);
        sb.append(line);
      }
      System.out.println("==================================");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null)
          br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return sb.toString();
  }

  private static String readBinaryFile(String name) {
    System.out.println("Not implemented yet!");
    return null;
  }
}