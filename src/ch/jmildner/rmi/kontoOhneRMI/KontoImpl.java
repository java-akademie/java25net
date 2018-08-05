package ch.jmildner.rmi.kontoOhneRMI;

public class KontoImpl
{
	private double mBalance;
	private String mName;


	public KontoImpl(String name, double balance)
	{
		mName = name;
		mBalance = balance;
	}


	public void einzahlen(double menge)
	{
		mBalance = mBalance + menge;
	}


	public double getKontostand()
	{
		return mBalance;
	}


	public String getName()
	{
		return mName;
	}


	@Override
	public String toString()
	{
		return getName() + "  " + getKontostand();
	}
}
