package ch.jmildner.net.primzahlen;

import java.io.IOException;

import ch.java_akademie.eetools.SocketChannel;

public class StartHandler implements Runnable
{
	private SocketChannel ch;


	public StartHandler(SocketChannel ch)
	{
		this.ch = ch;
	}


	@Override
	public void run()
	{
		try
		{
			ch.send(PrimServer.nextBigNumber());
			ch.send(PrimServer.nextBigNumber());
			System.out.println("BigNumber gesendet an " + ch);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
