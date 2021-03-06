package ch.jmildner.net.primzahlen;

import java.io.IOException;
import java.math.BigInteger;

import ch.jmildner.tools_ee.SocketChannel;



public class ResultHandler implements Runnable
{
	private SocketChannel ch;


	public ResultHandler(SocketChannel ch)
	{
		this.ch = ch;
	}


	@Override
	public void run()
	{
		boolean eof = false;
		BigInteger bigNumber = null;
		while (!eof)
		{
			try
			{
				Object o = ch.receive();
				if (o instanceof BigInteger)
				{
					bigNumber = (BigInteger) o;
					PrimServer.printBigNumber(bigNumber);
				}
				else
					eof = true;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("Primzahlen empfangen von " + ch);
		System.out.println("Bis jetzt " + PrimServer.nBigNumber
				+ " (wahrscheinliche) Primzahlen erhalten.");
	}
}
