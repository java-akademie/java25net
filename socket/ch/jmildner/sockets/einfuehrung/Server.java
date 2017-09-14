package ch.jmildner.sockets.einfuehrung;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import ch.jmb.tools.NetTools;

public class Server
{
	static ServerSocket serverSocket;
	static Socket socket;


	public static void main(String[] args) throws Exception
	{
		serverSocket = new ServerSocket(4711);

		System.out.println("wartet auf Anfrage ...");

		socket = serverSocket.accept();

		System.out.println("Anfrage erhalten ...");

		BufferedReader br = NetTools.getBufferedReader(socket);
		PrintWriter pw = NetTools.getPrintWriter(socket);

		System.out.println("BufferedReader und PrintWriter erstellt ");

		String info = br.readLine();

		System.out.println("gelesen ... " + info);

		pw.println(info + "/" + info);
		pw.flush();

		System.out.println("beendet");
	}

}
