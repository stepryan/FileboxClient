package filebox;


/**
* filebox/userPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../filebox.idl
* Tuesday, April 2, 2013 6:42:25 PM BST
*/

public abstract class userPOA extends org.omg.PortableServer.Servant
 implements filebox.userOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_id", new java.lang.Integer (0));
    _methods.put ("_get_username", new java.lang.Integer (1));
    _methods.put ("_set_username", new java.lang.Integer (2));
    _methods.put ("_get_password", new java.lang.Integer (3));
    _methods.put ("_set_password", new java.lang.Integer (4));
    _methods.put ("_get_lastlogin", new java.lang.Integer (5));
    _methods.put ("_set_lastlogin", new java.lang.Integer (6));
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
       case 0:  // filebox/user/_get_id
       {
         int $result = (int)0;
         $result = this.id ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // filebox/user/_get_username
       {
         String $result = null;
         $result = this.username ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 2:  // filebox/user/_set_username
       {
         String newUsername = in.read_string ();
         this.username (newUsername);
         out = $rh.createReply();
         break;
       }

       case 3:  // filebox/user/_get_password
       {
         String $result = null;
         $result = this.password ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       case 4:  // filebox/user/_set_password
       {
         String newPassword = in.read_string ();
         this.password (newPassword);
         out = $rh.createReply();
         break;
       }

       case 5:  // filebox/user/_get_lastlogin
       {
         int $result = (int)0;
         $result = this.lastlogin ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 6:  // filebox/user/_set_lastlogin
       {
         int newLastlogin = in.read_long ();
         this.lastlogin (newLastlogin);
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
    "IDL:filebox/user:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public user _this() 
  {
    return userHelper.narrow(
    super._this_object());
  }

  public user _this(org.omg.CORBA.ORB orb) 
  {
    return userHelper.narrow(
    super._this_object(orb));
  }


} // class userPOA