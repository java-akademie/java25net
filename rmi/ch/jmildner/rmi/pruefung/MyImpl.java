package ch.jmildner.rmi.pruefung;

public class MyImpl extends java.rmi.server.UnicastRemoteObject
		implements MyInterface
{
	private static final long serialVersionUID = 1L;


	public MyImpl() throws Exception
	{
	}


	@Override
	public double getWert() throws java.rmi.RemoteException
	{
		return 3.14159;
	}
}
