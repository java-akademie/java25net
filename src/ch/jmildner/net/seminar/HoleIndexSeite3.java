package ch.jmildner.net.seminar;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import ch.jmb.tools.NetTools;

public class HoleIndexSeite3
{

	static void holeIndexSeite1() throws Exception
	{
		Socket s = new Socket("www.wikileaks.ch", 80);

		PrintWriter pw = NetTools.getPrintWriter(s);
		BufferedReader br = NetTools.getBufferedReader(s);

		pw.println("GET /index.html HTTP/1.0");
		pw.println();
		pw.flush();

		String buffer;
		while ((buffer = br.readLine()) != null)
		{
			System.out.println(buffer);
		}

		pw.close();
		br.close();
		s.close();
	}


	static void holeIndexSeite2() throws Exception
	{
		Socket socket = new Socket("www.wikileaks.ch", 80);

		PrintWriter pw = NetTools.getPrintWriter(socket);
		BufferedReader br = NetTools.getBufferedReader(socket);

		pw.println("GET / HTTP/1.1");
		pw.println("Host: www.wikileaks.ch");
		pw.println("Content-Length: 100");
		pw.println("Content-Type: application/x-www-form-urlencoded");
		pw.println();
		pw.println("search=Katzen&go=Artikel");

		pw.flush();

		String buffer;
		while ((buffer = br.readLine()) != null)
		{
			System.out.println(buffer);
		}

		br.close();
		pw.close();

		socket.close();
	}


	public static void main(String[] args) throws Exception
	{
		holeIndexSeite2();
		// holeIndexSeite1();
	}
}
