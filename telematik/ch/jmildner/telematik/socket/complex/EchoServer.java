package ch.jmildner.telematik.socket.complex;

import ch.java_akademie.eetools.ChannelFactory;
import ch.java_akademie.eetools.Debug;

public class EchoServer
{
	private static Debug d = new Debug("EchoServer ... ", true);

	static final int PORT = 8088;


	public static void main(String[] args) throws Exception
	{
		d.debug("start");
		startServer(new ChannelFactory(PORT));
		System.out.println("EchoServer gestartet ...");
	}


	private static void startServer(final ChannelFactory cf)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				int i = 0;

				while (true)
				{
					try
					{
						EchoService ec;
						ec = new EchoService(cf.getChannel(), ++i);
						new Thread(ec).start();
					}
					catch (Exception e)
					{
						System.out.println(e);
					}
				}

			}
		}.start();
	}

}
