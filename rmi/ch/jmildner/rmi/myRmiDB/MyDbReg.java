package ch.jmildner.rmi.myRmiDB;

import java.rmi.Naming;

public class MyDbReg
{
	public static void main(String[] args)
	{
		registrieren("mydb");
	}


	public static void registrieren(String name)
	{
		try
		{
			MyDb mdb = new MyDbImpl();
			System.out.println("nach");
			Naming.rebind(name, mdb);
			System.out.println(name + " registriert");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
