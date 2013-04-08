package server;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.omg.CORBA.ORB;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import server.MessageServerImpl;
import server.ServiceServant;

import filebox.MessageServer;
import filebox.MessageServerHelper;
import filebox.MessageServerPOATie;
import filebox.listener;
import filebox.listenerHelper;
import filebox.service;
import filebox.servicePOATie;

public class Server {
  public static void main(String args[]) {
    try {
      // create and initialize the ORB
    	ORB orb = ORB.init(args, null);
    	POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
    	rootPOA.the_POAManager().activate();
    	// register the messageserver
    	MessageServerImpl msServer = new MessageServerImpl(rootPOA);
    	// register the service servant
    	msServer.setORB(orb);
    	ServiceServant handlerServant = new ServiceServant(rootPOA);
    	handlerServant.setORB(orb);
    	// register the listener for call back
    	ListenerImpl listen = new ListenerImpl();
        rootPOA.activate_object(listen);
        listener reference = listenerHelper.narrow(rootPOA.servant_to_reference(listen));
        msServer.register(reference);
    	MessageServerPOATie tie = new MessageServerPOATie(msServer, rootPOA);
    	org.omg.CORBA.Object ref = rootPOA.servant_to_reference(tie);
    	servicePOATie serviceTie = new servicePOATie(handlerServant, rootPOA);
    	org.omg.CORBA.Object ref2 = rootPOA.servant_to_reference(serviceTie);
    	//MessageServer href2 = tie._this(orb);
    	//service href = serviceTie._this(orb);
    	org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
    	NamingContextExt namingref = NamingContextExtHelper.narrow(objref);
    	NamingContextExt namingref2 = NamingContextExtHelper.narrow(objref);
    	String messageServer = "MessageServer";
    	String serverServant = "ServerServant";
    	NameComponent path[] = namingref.to_name(messageServer);
    	NameComponent path2[] = namingref2.to_name(serverServant);
    	
    	namingref.rebind(path, ref2);
    	namingref2.rebind(path2, ref);
     
      System.out.println("Object ref is " + orb.object_to_string(objref));

      FileOutputStream fos = new FileOutputStream("filebox.ior");
      PrintStream ps = new PrintStream(fos);
      ps.print(orb.object_to_string(objref));
      ps.close();
      //msServer.startReadThread();
      System.out.println("Server is ready...");
      orb.run();
      System.out.println("orb running");
      
    } catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }

    System.out.println("FileBoxServer exiting ...");
  }
}