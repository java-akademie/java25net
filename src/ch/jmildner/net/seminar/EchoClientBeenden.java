package ch.jmildner.net.seminar;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClientBeenden
{

	public static void main(String[] args)
	{
		System.out.println("start EchoServer beenden aufrufen");
		
		Socket socket;
		
		try
		{
			socket = new Socket("localhost", 7000, null, 64000);
			socket.close();
		}
		catch (ConnectException e)
		{
			System.out.println("Keine Server aktiv!");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("stopp EchoServer beenden aufrufen");
	}
}
