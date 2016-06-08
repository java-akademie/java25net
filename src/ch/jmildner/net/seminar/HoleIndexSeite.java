package ch.jmildner.net.seminar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;



public class HoleIndexSeite
{
	public static void main(String[] args) throws Exception
	{
		Socket socket = new Socket("localhost", 8080);

		PrintWriter pw = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		BufferedReader br = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		pw.println("POST /nettest/nettest.jsp HTTP/1.1");
		pw.println("Host: localhost");
		pw.println("Content-Length: 24");
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
}
