package ch.jmildner.telematik.dbServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ch.java_akademie.tools.MyTools;
import ch.java_akademie.tools.MyTools2;

public class DbServer extends Thread
{
	static final int PORT = 8088;
	static boolean weiter = true;
	static ServerSocket serverSocket;


	public static void main(String[] args) throws IOException
	{
		DbServer dbs = new DbServer(PORT);
		dbs.start();
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


	public DbServer(int port) throws IOException
	{
		serverSocket = MyTools2.getServerSocket(port);
	}


	@Override
	public void run()
	{
		System.out.println("\n\n\n---------------------");
		System.out.println("DataBase-Server gestartet\n");
		DbKunde dbk = new DbKunde();
		int i = 0;
		while (weiter)
		{
			Socket socket;
			try
			{
				socket = MyTools2.getSocket(serverSocket);
				if (weiter)
				{
					new DbService(socket, ++i, dbk).start();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		dbk.closeDB();
		System.out.println("DataBase-Server gestoppt");
		System.out.println("\n\n\n---------------------");
	}
}
