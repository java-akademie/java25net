package ch.jmildner.net.seminar;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import ch.java_akademie.tools.NetTools;

public class EchoClient
{

	public static void main(String[] args) throws Exception
	{
		Socket socket = new Socket("localhost", 7);

		BufferedReader br = NetTools.getBufferedReader(socket);
		PrintWriter pw = NetTools.getPrintWriter(socket);

		String buff;

		for (int i = 1; i <= 100000; i++)
		{
			pw.println("anfrage-" + i);
			pw.flush();
			buff = br.readLine();
			System.out.println(buff);
		}
		pw.println("$$$");
		pw.flush();
		br.close();
		pw.close();
		socket.close();
	}
}
