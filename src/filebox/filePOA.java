package filebox;


/**
* filebox/filePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from filebox.idl
* 03 April 2013 20:38:14 o'clock IST
*/

public abstract class filePOA extends org.omg.PortableServer.Servant
 implements filebox.fileOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_id", new java.lang.Integer (0));
    _methods.put ("_get_name", new java.lang.Integer (1));
    _methods.put ("_set_name", new java.lang.Integer (2));
    _methods.put ("_get_content", new java.lang.Integer (3));
    _methods.put ("_set_content", new java.lang.Integer (4));
    _methods.put ("_get_status", new java.lang.Integer (5));
    _methods.put ("_set_status", new java.lang.Integer (6));
    _methods.put ("_get_userid", new java.lang.Integer (7));
    _methods.put ("_set_userid", new java.lang.Integer (8));
    _methods.put ("_get_contentbinary", new java.lang.Integer (9));
    _methods.put ("_set_contentbinary", new java.lang.Integer (10));
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
       case 0:  // filebox/file/_get_id
       {
         int $result = (int)0;
         $result = this.id ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // filebox/file/_get_name
       {
         String $result = null;
         $result = this.name ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // filebox/file/_set_name
       {
         String newName = in.read_string ();
         this.name (newName);
         out = $rh.createReply();
         break;
       }

       case 3:  // filebox/file/_get_content
       {
         String $result = null;
         $result = this.content ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // filebox/file/_set_content
       {
         String newContent = in.read_string ();
         this.content (newContent);
         out = $rh.createReply();
         break;
       }

       case 5:  // filebox/file/_get_status
       {
         int $result = (int)0;
         $result = this.status ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 6:  // filebox/file/_set_status
       {
         int newStatus = in.read_long ();
         this.status (newStatus);
         out = $rh.createReply();
         break;
       }


  // 0 - private, 1 - public
       case 7:  // filebox/file/_get_userid
       {
         int $result = (int)0;
         $result = this.userid ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }


  // 0 - private, 1 - public
       case 8:  // filebox/file/_set_userid
       {
         int newUserid = in.read_long ();
         this.userid (newUserid);
         out = $rh.createReply();
         break;
       }

       case 9:  // filebox/file/_get_contentbinary
       {
         org.omg.CORBA.Any $result = null;
         $result = this.contentbinary ();
         out = $rh.createReply();
         out.write_any ($result);
         break;
       }

       case 10:  // filebox/file/_set_contentbinary
       {
         org.omg.CORBA.Any newContentbinary = in.read_any ();
         this.contentbinary (newContentbinary);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:filebox/file:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public file _this() 
  {
    return fileHelper.narrow(
    super._this_object());
  }

  public file _this(org.omg.CORBA.ORB orb) 
  {
    return fileHelper.narrow(
    super._this_object(orb));
  }


} // class filePOA
