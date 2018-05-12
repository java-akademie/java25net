package ch.jmildner.rechner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import ch.jmildner.tools.MyTools;

public class RechnerClient extends Thread
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("start des Rechner Clients");
		new RechnerClient();
		System.out.println("stopp des Rechner Clients");
	}
	Socket socket;
	BufferedReader br;


	PrintWriter pw;


	public RechnerClient() throws Exception
	{
		socket = new Socket("localhost", 8080);
		br = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		pw = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		double wert1 = MyTools.getDouble("bitte wert1 eingeben > ");
		double wert2 = MyTools.getDouble("bitte wert2 eingeben > ");
		String op = MyTools.getString("bitte op    eingeben > ");
		pw.println(wert1 + wert2 + op);
		pw.flush();
		System.out.println(br.readLine());
	}
}
