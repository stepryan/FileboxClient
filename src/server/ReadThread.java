package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReadThread extends Thread {
  MessageServerImpl clientmessage = null;

  public ReadThread(MessageServerImpl message) {
    this.clientmessage = message;
  }

  public void run() {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try {
      for (;;) {
        System.out.print("message >  ");
        String usermessage = br.readLine();
        clientmessage.message(usermessage);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
