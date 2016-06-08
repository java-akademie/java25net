package ch.jmildner.telematik.socket.complex;

import ch.java_akademie.eetools.Debug;
import ch.java_akademie.eetools.SocketChannel;

public class EchoService extends Thread
{
	private static Debug d = new Debug("EchoService ... ", true);

	SocketChannel ch;
	String buff;
	int threadNummer;


	public EchoService(SocketChannel ch, int i)
	{
		d.debug("start");
		this.ch = ch;
		this.threadNummer = i;
	}


	@Override
	public void run()
	{
		try
		{
			buff = (String) ch.receive();
			System.out.println("vom Client empfangen: <" + buff + ">");
			ch.send("daten des " + threadNummer + ". Thread: " + buff);
			ch.send(buff + " 111");
			ch.send(buff + " 222");
			ch.send(buff + " 333");
			ch.send("#eof#");
			System.out.println(threadNummer + ". Thread beendet");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
