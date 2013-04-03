package filebox;


/**
* filebox/statusPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../filebox.idl
* Tuesday, April 2, 2013 6:42:25 PM BST
*/

public abstract class statusPOA extends org.omg.PortableServer.Servant
 implements filebox.statusOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_code", new java.lang.Integer (0));
    _methods.put ("_set_code", new java.lang.Integer (1));
    _methods.put ("_get_text", new java.lang.Integer (2));
    _methods.put ("_set_text", new java.lang.Integer (3));
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
       case 0:  // filebox/status/_get_code
       {
         int $result = (int)0;
         $result = this.code ();
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       case 1:  // filebox/status/_set_code
       {
         int newCode = in.read_long ();
         this.code (newCode);
         out = $rh.createReply();
         break;
       }


  // 0 - success, 1 - error
       case 2:  // filebox/status/_get_text
       {
         String $result = null;
         $result = this.text ();
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }


  // 0 - success, 1 - error
       case 3:  // filebox/status/_set_text
       {
         String newText = in.read_string ();
         this.text (newText);
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
    "IDL:filebox/status:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public status _this() 
  {
    return statusHelper.narrow(
    super._this_object());
  }

  public status _this(org.omg.CORBA.ORB orb) 
  {
    return statusHelper.narrow(
    super._this_object(orb));
  }


} // class statusPOA