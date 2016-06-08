package ch.jmildner.rmi.pruefung;

public class MyReg
{
	public static void main(String[] args) throws Exception
	{
		MyImpl mi = new MyImpl();
		java.rmi.Naming.rebind("mi", mi);
		System.out.println("mi registriert");
	}
}
