package ch.jmildner.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import ch.jmildner.tools.MyTools;

public class EchoService extends Thread
{
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	String buff;
	int threadNummer;


	public EchoService(Socket socket, int i)
	{
		this.socket = socket;
		this.threadNummer = i;
	}


	@Override
	public void run()
	{
		try
		{
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));

			String line = br.readLine();
			System.out.println("line=<" + line + ">");

			while (!line.equals(""))
			{
				buff += "\n" + line;
				line = br.readLine();
				System.out.println("line=<" + line + ">");
				pw.println(line);
			}

			MyTools.sleep(2500);
			pw.println("daten des .... " + threadNummer + ". Thread: "
					+ buff);
			pw.println("hugo");
			pw.println("hugo");
			pw.println("hugo");
			pw.flush();
			System.out
					.println("\n" + threadNummer + ". Thread beendet");
			System.out.println("Buffer=<" + buff + ">");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
