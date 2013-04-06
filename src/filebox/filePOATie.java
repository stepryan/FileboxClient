package filebox;


/**
* filebox/filePOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from filebox.idl
* 03 April 2013 20:38:14 o'clock IST
*/

public class filePOATie extends filePOA
{

  // Constructors

  public filePOATie ( filebox.fileOperations delegate ) {
      this._impl = delegate;
  }
  public filePOATie ( filebox.fileOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public filebox.fileOperations _delegate() {
      return this._impl;
  }
  public void _delegate (filebox.fileOperations delegate ) {
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
  public int id ()
  {
    return _impl.id();
  } // id

  public String name ()
  {
    return _impl.name();
  } // name
  public void name (String newName)
  {
    _impl.name(newName);
  } // name

  public String content ()
  {
    return _impl.content();
  } // content
  public void content (String newContent)
  {
    _impl.content(newContent);
  } // content

  public int status ()
  {
    return _impl.status();
  } // status
  public void status (int newStatus)
  {
    _impl.status(newStatus);
  } // status


  // 0 - private, 1 - public
  public int userid ()
  {
    return _impl.userid();
  } // userid

  // 0 - private, 1 - public
  public void userid (int newUserid)
  {
    _impl.userid(newUserid);
  } // userid

  public org.omg.CORBA.Any contentbinary ()
  {
    return _impl.contentbinary();
  } // contentbinary
  public void contentbinary (org.omg.CORBA.Any newContentbinary)
  {
    _impl.contentbinary(newContentbinary);
  } // contentbinary

  private filebox.fileOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class filePOATie
