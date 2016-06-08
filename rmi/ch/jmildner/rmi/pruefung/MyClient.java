package ch.jmildner.rmi.pruefung;

public class MyClient
{
	public static void main(String[] args) throws Exception
	{
		MyInterface mi;
		mi = (MyInterface) java.rmi.Naming.lookup("rmi://localhost/mi");
		System.out.println(mi.getWert());
	}
}
