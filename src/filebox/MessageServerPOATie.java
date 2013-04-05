package filebox;


/**
* filebox/MessageServerPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from filebox.idl
* 03 April 2013 20:38:14 o'clock IST
*/

public class MessageServerPOATie extends MessageServerPOA
{

  // Constructors

  public MessageServerPOATie ( filebox.MessageServerOperations delegate ) {
      this._impl = delegate;
  }
  public MessageServerPOATie ( filebox.MessageServerOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public filebox.MessageServerOperations _delegate() {
      return this._impl;
  }
  public void _delegate (filebox.MessageServerOperations delegate ) {
      this._impl = delegate;
  }
  public org.omg.PortableServer.POA _default_POA() {
      if(_poa != null) {
          return _poa;
      }
      else {
          return super._default_POA();
      }
  }
  public void register (filebox.listener lt)
  {
    _impl.register(lt);
  } // register

  private filebox.MessageServerOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class MessageServerPOATie