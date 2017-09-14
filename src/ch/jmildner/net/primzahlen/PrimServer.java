package ch.jmildner.net.primzahlen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Random;

import ch.jmb.tools_ee.ChannelFactory;


public class PrimServer
{
	private static BigInteger bigNumber = new BigInteger(512, new Random());
	private static PrintStream primFile = null;
	public static int nBigNumber = 0;


	public static void main(String[] args)
	{

		System.out.println((bigNumber + "").length());
		System.out.println(bigNumber);

		// port 4711: hier holen sich die Clients den Startwert ab dem
		// aus den naechsten 2000 BigIntegers die Primzahlen ermittelt
		// werden sollen

		PrimServer.startServer(new ChannelFactory(4711));

		// port 4712: hier geben die Clients die ermittelten Primzahlen
		// zurueck

		PrimServer.resultServer(new ChannelFactory(4712));

		System.out.println("PrimServer gestartet ... ");
	}


	synchronized public static BigInteger nextBigNumber()
	{
		bigNumber = bigNumber.add(new BigInteger("2000"));
		return bigNumber;
	}


	synchronized public static void printBigNumber(BigInteger bigNumber)
	{
		if (primFile == null)
		{
			try
			{
				primFile = new PrintStream(new FileOutputStream("prim.txt"));
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		primFile.println(bigNumber);
		nBigNumber++;
	}


	public static void resultServer(final ChannelFactory cf)
	{
		Runnable runLoop = new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						new Thread(new ResultHandler(cf.getChannel())).start();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		};

		new Thread(runLoop).start();
	}


	public static void startServer(final ChannelFactory cf)
	{

		Runnable runLoop = new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{
					try
					{

						new Thread(new StartHandler(cf.getChannel())).start();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(runLoop).start();
	}


}
