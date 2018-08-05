package ch.jmildner.sockets.einfuehrung;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server2
{
	private static void acceptClient(ServerSocket serverSocket)
			throws Exception
	{
		Socket socket = serverSocket.accept();

		System.out.println("Server: Anfrage erhalten ...");

		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		PrintWriter pw = new PrintWriter(osw);

		System.out
				.println("Server: BufferedReader und PrintWriter erstellt ...");

		String info = br.readLine();

		System.out.println("Server: gelesen ... " + info);

		pw.println(info + "/" + info);
		pw.flush();
	}


	public static void main(String[] args) throws Exception
	{
		ServerSocket serverSocket = new ServerSocket(4711);

		while (true)
		{
			acceptClient(serverSocket);
		}
	}

}
