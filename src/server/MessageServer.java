import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.Iterator;
import filebox.*;

public class MessageServer extends MessageServerPOA {
	private Vector fileclients = new Vector();
	private ReadThread rthread = null;
	
	public MessageServer(){
		rthread = new ReadThread(this);
		
		
	}
	public void register(listener lt){
		 fileclients.add(lt);
		 }
	 public void startReadThread(){
		 rthread.start();
	 }
	 
	 public void message(){
		 Iterator it = fileclients.iterator();
		 while(it.hasNext()){
			 listener lt = (listener) it.next();
			  String message = " file updated";
			  lt.message(message);
			 
		 }
	 }
	
	 
	 
}