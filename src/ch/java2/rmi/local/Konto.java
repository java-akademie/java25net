package ch.java2.rmi.local;

public interface Konto
{
	public double getKontostand();


	public String getName();


	public void verbuchen(double betrag);
}
