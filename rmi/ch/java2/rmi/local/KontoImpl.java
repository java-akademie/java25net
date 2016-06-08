package ch.java2.rmi.local;

public class KontoImpl implements Konto
{
	private double kontostand = 0;
	private String name = "";


	public KontoImpl(String name)
	{
		this.name = name;
	}


	@Override
	public double getKontostand()
	{
		return kontostand;
	}


	@Override
	public String getName()
	{
		return name;
	}


	@Override
	public void verbuchen(double betrag)
	{
		kontostand = kontostand + betrag;
	}
}
