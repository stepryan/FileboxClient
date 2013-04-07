package server;

import java.util.Vector;
import java.util.Iterator;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import filebox.*;

public class MessageServerImpl extends MessageServerPOA {
  @SuppressWarnings("rawtypes")
  private Vector<listener> fileclients = new Vector<listener>();
  private ReadThread rthread = null;
  private  ORB orb;
  private POA rootPOA;
  public MessageServerImpl() {
	  super();
    rthread = new ReadThread(this);
    }
  
  public MessageServerImpl(POA root){
	  this();
	  this.rootPOA = root;
  }
  
  
 
  public void message(String usermessage) {
    @SuppressWarnings("rawtypes")
    Iterator it = fileclients.iterator();
    
    while (it.hasNext()) {
      listener lt = (listener) it.next();
      String message = " file updated";
      lt.message(message);
    }
  }

public  void setORB(ORB orb_val) {	
		 orb = orb_val;
		 
}

public void shutdown(){
    orb.shutdown(false);
  }



@Override
public void register(listener lt) {
	fileclients.add(lt);
	
}

public void startReadThread() {
	rthread.start();
	
}


}