package filebox;


/**
* filebox/usersHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ../filebox.idl
* Tuesday, April 2, 2013 6:42:25 PM BST
*/

abstract public class usersHelper
{
  private static String  _id = "IDL:filebox/users:1.0";

  public static void insert (org.omg.CORBA.Any a, filebox.user[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static filebox.user[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = filebox.userHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (filebox.usersHelper.id (), "users", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static filebox.user[] read (org.omg.CORBA.portable.InputStream istream)
  {
    filebox.user value[] = null;
    int _len0 = istream.read_long ();
    value = new filebox.user[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = filebox.userHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, filebox.user[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      filebox.userHelper.write (ostream, value[_i0]);
  }

}