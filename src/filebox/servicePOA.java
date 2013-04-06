package filebox;


/**
* filebox/servicePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from filebox.idl
* 03 April 2013 20:38:14 o'clock IST
*/

public abstract class servicePOA extends org.omg.PortableServer.Servant
 implements filebox.serviceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("addUser", new java.lang.Integer (0));
    _methods.put ("getUser", new java.lang.Integer (1));
    _methods.put ("login", new java.lang.Integer (2));
    _methods.put ("addFile", new java.lang.Integer (3));
    _methods.put ("getFile", new java.lang.Integer (4));
    _methods.put ("changeFileStatus", new java.lang.Integer (5));
    _methods.put ("fileUpdate", new java.lang.Integer (6));
    _methods.put ("getFiles", new java.lang.Integer (7));
    _methods.put ("removeFile", new java.lang.Integer (8));
    _methods.put ("register", new java.lang.Integer (9));
    _methods.put ("getStatus", new java.lang.Integer (10));
    _methods.put ("addFileBinary", new java.lang.Integer (11));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // filebox/service/addUser
       {
         String username = in.read_string ();
         String password = in.read_string ();
         int $result = (int)0;
         $result = this.addUser (username, password);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // filebox/service/getUser
       {
         int id = in.read_long ();
         filebox.user $result = null;
         $result = this.getUser (id);
         out = $rh.createReply();
         filebox.userHelper.write (out, $result);
         break;
       }

       case 2:  // filebox/service/login
       {
         String username = in.read_string ();
         String password = in.read_string ();
         int $result = (int)0;
         $result = this.login (username, password);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 3:  // filebox/service/addFile
       {
         String name = in.read_string ();
         String content = in.read_string ();
         int status = in.read_long ();
         int userid = in.read_long ();
         int $result = (int)0;
         $result = this.addFile (name, content, status, userid);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 4:  // filebox/service/getFile
       {
         String filename = in.read_string ();
         int uid = in.read_long ();
         filebox.file $result = null;
         $result = this.getFile (filename, uid);
         out = $rh.createReply();
         filebox.fileHelper.write (out, $result);
         break;
       }

       case 5:  // filebox/service/changeFileStatus
       {
         String filelist = in.read_string ();
         short fileid = in.read_short ();
         short filestatus = in.read_short ();
         filebox.file $result = null;
         $result = this.changeFileStatus (filelist, fileid, filestatus);
         out = $rh.createReply();
         filebox.fileHelper.write (out, $result);
         break;
       }

       case 6:  // filebox/service/fileUpdate
       {
         String filelist = in.read_string ();
         int lastchanged = in.read_long ();
         short fileid = in.read_short ();
         filebox.listener callback = filebox.listenerHelper.read (in);
         filebox.file $result = null;
         $result = this.fileUpdate (filelist, lastchanged, fileid, callback);
         out = $rh.createReply();
         filebox.fileHelper.write (out, $result);
         break;
       }

       case 7:  // filebox/service/getFiles
       {
         int uid = in.read_long ();
         String content = in.read_string ();
         String filename = in.read_string ();
         filebox.file $result[] = null;
         $result = this.getFiles (uid, content, filename);
         out = $rh.createReply();
         filebox.filesHelper.write (out, $result);
         break;
       }

       case 8:  // filebox/service/removeFile
       {
         short fileid = in.read_short ();
         this.removeFile (fileid);
         out = $rh.createReply();
         break;
       }

       case 9:  // filebox/service/register
       {
         filebox.listener lt = filebox.listenerHelper.read (in);
         this.register (lt);
         out = $rh.createReply();
         break;
       }

       case 10:  // filebox/service/getStatus
       {
         filebox.status $result = null;
         $result = this.getStatus ();
         out = $rh.createReply();
         filebox.statusHelper.write (out, $result);
         break;
       }

       case 11:  // filebox/service/addFileBinary
       {
         org.omg.CORBA.Any contentbinary = in.read_any ();
         String filename = in.read_string ();
         short fileid = in.read_short ();
         short filestatus = in.read_short ();
         filebox.file $result = null;
         $result = this.addFileBinary (contentbinary, filename, fileid, filestatus);
         out = $rh.createReply();
         filebox.fileHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:filebox/service:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public service _this() 
  {
    return serviceHelper.narrow(
    super._this_object());
  }

  public service _this(org.omg.CORBA.ORB orb) 
  {
    return serviceHelper.narrow(
    super._this_object(orb));
  }


} // class servicePOA
