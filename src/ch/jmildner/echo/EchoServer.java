package ch.jmildner.echo;

import java.net.ServerSocket;
import java.net.Socket;

import ch.java_akademie.tools.MyTools;

public class EchoServer extends Thread
{
	static final int PORT = 8080;
	static boolean weiter = true;
	static ServerSocket serverSocket;


	public static void main(String[] args) throws Exception
	{
		EchoServer es = new EchoServer(PORT);
		es.start();
		MyTools.sleep(100);
		while (weiter)
		{
			String q = MyTools.getString("zum beenden 'q' eingeben\n");
			if (q.equals("q"))
			{
				weiter = false;
				// Socket s = MyTools.getSocket("localhost", PORT);
			}
		}
	}


	public EchoServer(int port) throws Exception
	{
		serverSocket = new ServerSocket(port);
	}


	@Override
	public void run()
	{
		try
		{
			System.out.println("\n\n\n---------------------");
			System.out.println("Echo-Server gestartet\n");
			int i = 0;
			while (weiter)
			{
				Socket socket = serverSocket.accept();
				if (weiter)
				{
					new EchoService(socket, ++i).start();
				}
			}
			System.out.println("Echo-Server gestoppt");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
