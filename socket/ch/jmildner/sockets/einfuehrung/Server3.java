package ch.jmildner.sockets.einfuehrung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server3
{
	public static void main(String[] args) throws Exception
	{
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(4711);

		while (true)
		{

			System.out.println("Server: wartet auf Anfrage ...");
			Service s = new Service(serverSocket.accept());
			s.start();
		}
	}

}



class Service extends Thread
{
	Socket socket;


	Service(Socket socket)
	{
		this.socket = socket;
	}


	@Override
	public void run()
	{
		try
		{
			System.out.println("Server: Anfrage erhalten ...");

			InputStream is;
			is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osw);

			System.out
					.println("Server: BufferedReader und PrintWriter erstellt ...");

			String info = br.readLine();

			System.out.println("Server: gelesen ... " + info);

			pw.println("zeile1: " + info + "/" + info);
			pw.println("zeile2: " + info + "/" + info);
			pw.println("zeile3: " + info + "/" + info);
			pw.flush();
			pw.println("");
			pw.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
