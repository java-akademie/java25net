package ch.jmildner.rechner;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import ch.java_akademie.tools.NetTools;

public class RechnerServer
{
	static class RechnerService extends Thread
	{
		Socket socket;


		RechnerService(Socket socket)
		{
			this.socket = socket;
		}


		private String berechne(String frage)
		{
			return "weis ich nicht";
		}


		@Override
		public void run()
		{
			PrintWriter pw;
			BufferedReader br;

			try
			{
				pw = NetTools.getPrintWriter(socket);
				br = NetTools.getBufferedReader(socket);
				String frage = br.readLine();
				pw.println(berechne(frage));
				pw.flush();
				br.close();
				pw.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
	}
	static final int PORT = 8080;
	static boolean weiter = true;


	static ServerSocket serverSocket;


	public static void main(String[] args) throws Exception
	{
		System.out.println("Rechner Server gestartet!");

		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(PORT);
		while (true)
		{
			Socket socket = ss.accept();
			new RechnerService(socket).start();
		}
	}
}
