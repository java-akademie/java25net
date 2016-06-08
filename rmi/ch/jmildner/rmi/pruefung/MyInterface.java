package ch.jmildner.rmi.pruefung;

public interface MyInterface extends java.rmi.Remote
{
	double getWert() throws java.rmi.RemoteException;
}
