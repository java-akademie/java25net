package ch.jmildner.net.primzahlen;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Vector;

import ch.jmb.tools_ee.ChannelFactory;
import ch.jmb.tools_ee.SocketChannel;


public class PrimClient
{
	public static void main(String[] args) throws Exception
	{
		String host;

		if (args.length > 0)
		{
			host = args[0];
		}
		else
		{
			host = "localhost";
		}

		while (true)
		{
			Vector<BigInteger> bigNumberList = new Vector<BigInteger>();

			SocketChannel ch = ChannelFactory.getChannel(host, 4711);

			int i = 0;

			System.out.println("PrimSearch gestartet...");

			BigInteger bigNumber = (BigInteger) ch.receive();

			System.out.println(bigNumber);

			BigInteger bigNumberEnd = (BigInteger) ch.receive();

			System.out.println(bigNumberEnd);

			ch.close();

			while (bigNumber.compareTo(bigNumberEnd) < 0)
			{
				// bigNumber pr�fen, wenn Primzahl, dann in Liste
				// aufnehmen...
				if (bigNumber.isProbablePrime(Integer.MAX_VALUE - 1))
				{
					System.out.print("#");
					bigNumberList.addElement(bigNumber);
				}
				else
				{
					// Nur zur Anzeige, dass der Client noch lebt...
					System.out.print(".");
				}

				i++;

				if (i % 100 == 0)
				{
					System.out.println();
				}
				bigNumber = bigNumber.add(BigInteger.ONE);
			}

			// Gefundene Primzahlen senden...
			if (bigNumberList.isEmpty())
			{
				continue;
			}

			ch = ChannelFactory.getChannel(host, 4712);

			for (Enumeration<BigInteger> e = bigNumberList.elements(); e.hasMoreElements();)
			{
				BigInteger bigPrim = e.nextElement();
				ch.send(bigPrim);
			}

			ch.send("-EOF-");

			System.out.println("PrimSearch beendet.");

			ch.close();
		}
	}
}
