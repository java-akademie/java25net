package ch.jmildner.rmi.kontoOhneRMI;

public class MyClient
{
	public static void main(String[] args)
	{
		MyImpl mi = new MyImpl();
		double pi = mi.getWert();
		System.out.println(pi);
	}
}
