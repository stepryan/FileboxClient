package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.Iterator;
import filebox.*;

public class MessageServerImpl extends MessageServerPOA {
	@SuppressWarnings("rawtypes")
	private Vector fileclients = new Vector();
	private ReadThread rthread = null;
	
	public MessageServerImpl(){
		rthread = new ReadThread(this);
		
		
	}
	@SuppressWarnings("unchecked")
	public void register(listener lt){
		 fileclients.add(lt);
		 }
	 public void startReadThread(){
		 rthread.start();
	 }
	 
	 public void message(String usermessage){
		 @SuppressWarnings("rawtypes")
		Iterator it = fileclients.iterator();
		 while(it.hasNext()){
			 listener lt = (listener) it.next();
			  String message = " file updated";
			  lt.message(message);
			 
		 }
	 }
	
	 
	 
}