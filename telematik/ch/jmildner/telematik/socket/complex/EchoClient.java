package ch.jmildner.telematik.socket.complex;


import ch.java_akademie.eetools.ChannelFactory;
import ch.java_akademie.eetools.Debug;
import ch.java_akademie.eetools.SocketChannel;

public class EchoClient extends Thread
{
	private static Debug d = new Debug("EchoClient ... ", true);


	public static void main(String[] args) throws Exception
	{
		for (int i = 1; i <= 100; i++)
		{
			test(i);
		}
	}


	static void test(final int i)
	{
		d.debug("start");

		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					String buff;

					SocketChannel ch;
					ch = ChannelFactory.getChannel("localhost", 8088);

					ch.send("anfrage-" + i);
					buff = (String) ch.receive();
					while (!buff.equals("#eof#"))
					{
						System.out.println("... " + buff);
						buff = (String) ch.receive();
					}
				}
				catch (Exception e)
				{
				}
			}
		}.start();


	}
}
