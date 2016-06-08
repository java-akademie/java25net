package ch.jmildner.telematik.dbServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ch.java_akademie.tools.MyTools2;

public class DbService extends Thread
{
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	DbKunde dbk;
	int threadNummer;


	public DbService(Socket socket, int i, DbKunde dbk)
	{
		this.socket = socket;
		this.threadNummer = i;
		this.dbk = dbk;
	}


	@Override
	public void run()
	{
		try
		{
			br = MyTools2.getBufferedReader(socket);
			pw = MyTools2.getPrintWriter(socket);
			String id = "";
			do
			{
				try
				{
					id = MyTools2.readLine(br);
					if (id.equals("2410"))
					{
						dbk.insertKunde();
					}
					MyTools2.writeLine(pw,
							threadNummer + ". Ergebnis: " + id + "=" + dbk.getKundenName(Integer.parseInt(id)));
					MyTools2.flush(pw);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			while (!id.equals("0"));
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

		System.out.println("\n" + threadNummer + ". Thread beendet");
	}
}
