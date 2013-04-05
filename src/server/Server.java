package server;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.omg.CORBA.ORB;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import filebox.MessageServer;
import filebox.MessageServerHelper;
import filebox.filePOATie;
import filebox.servicePOATie;
import filebox.userPOATie;

public class Server {
  public static void main(String args[]) {
    try {
      // create and initialize the ORB
      ORB orb = ORB.init(args, null);

      /*
       * Because its POA, we need to get rootPOA object to bind IOR to tie object we created
       */

      org.omg.CORBA.Object objRefPOA = orb.resolve_initial_references("RootPOA");
      POA rootPOA = POAHelper.narrow(objRefPOA);

      

      // create servant and register it with the ORB
      ServiceServant handlerServant = new ServiceServant(rootPOA);
      // create a tie, with servant being the delegate
      servicePOATie serviceTie = new servicePOATie(handlerServant);
      MessageServerImpl msServer = new MessageServerImpl();
      
      byte[] objID = rootPOA.activate_object(serviceTie);
      org.omg.CORBA.Object shopServantObjectRef = rootPOA.id_to_reference(objID);
      
      rootPOA.activate_object(msServer);
      MessageServer msref = MessageServerHelper.narrow(rootPOA.servant_to_reference(msServer));
      NamingContext nContext = NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
      System.out.println("Contacted naming Service");
      NameComponent[] nComponent = {new NameComponent("MessageServer", "")};
      nContext.rebind(nComponent, msref);
      rootPOA.the_POAManager().activate();
      
      System.out.println("Object ref is " + orb.object_to_string(shopServantObjectRef));
      
      FileOutputStream fos = new FileOutputStream("filebox.ior");
      PrintStream ps = new PrintStream(fos);
      ps.print(orb.object_to_string(shopServantObjectRef)); 
      ps.close();

      System.out.println("Server is ready...");
      orb.run();
    } catch (Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }

    System.out.println("FileBoxServer exiting ...");
  }
}