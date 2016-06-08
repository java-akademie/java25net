package ch.jmildner.echo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient extends Thread
{
	public static void main(String[] args) throws Exception
	{
		System.out.println("start des echoClients");
		new EchoClient();
		System.out.println("stopp des echoClients");
	}
	Socket socket;
	BufferedReader br;


	PrintWriter pw;


	public EchoClient() throws Exception
	{
		socket = new Socket("localhost", 8080);
		br = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		pw = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()));
		// pw.println("POST /wcds/index.jsp HTTP/1.0");
		pw.println("GET /wcds2011/start.html HTTP/1.0");
		pw.println("Content-Length: 20");
		pw.println("");
		pw.println("x=aaa&y=bbb&z=xxxxxxxxxxxxxxxxxxxxxxx");
		pw.flush();
		String buff = br.readLine();
		while (buff != null)
		{
			System.out.println("..." + buff);
			buff = br.readLine();
		}
	}
}
