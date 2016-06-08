package ch.jmildner.telematik.socket.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * dieser SimpleSocketServer akzeptiert eine Anfrage eines Clients am
 * Port 4711, verarbeitet diese Anfrage, sendet das Ergebnis zurueck und
 * beendet sich danach wieder
 * 
 * Test: c:\>telnet localhost 4711 ... mit $ beenden
 * 
 * @author johann
 * 
 */
public class SimpleSocketServer
{
	static ServerSocket serverSocket;
	static Socket socket;
	static String buff;

	static BufferedReader br;
	static PrintWriter pw;


	static void acceptClient() throws IOException
	{
		socket = serverSocket.accept();
		System.out.println("Verbindung hergestellt ...");

		pw = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		br = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		while (!(buff = br.readLine()).equals("$"))
		{
			pw.println(":" + buff);
			pw.flush();
		}
	}


	public static void main(String[] args) throws IOException
	{
		startServer();
		acceptClient();

		System.out.println("programmende ...");
	}


	static void startServer() throws IOException
	{
		serverSocket = new ServerSocket(4711);
		System.out.println("server wartet auf client ...");
	}
}
