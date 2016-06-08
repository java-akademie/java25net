package ch.jmildner.net.seminar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.Socket;

import ch.java_akademie.tools.NetTools;

public class DayTime
{
	public static void main(String[] args) throws Exception
	{
		test1();
		test2();
	}


	private static void test1() throws Exception
	{

		Socket socket = new Socket("time.nist.gov", 13);
		InputStream is = socket.getInputStream();

		int len;
		byte[] b = new byte[1000];

		while ((len = is.read(b)) != -1)
		{
			System.out.write(b, 0, len);
		}

		is.close();
		socket.close();
	}


	private static void test2() throws Exception
	{
		System.out.println("xxx");

		Socket socket = new Socket("time.nist.gov", 13);
		// InputStream is = socket.getInputStream();

		BufferedReader br = new BufferedReader(
				NetTools.getBufferedReader(socket));

		String s = br.readLine();
		System.out.println("xxx" + s + "xxx");
		System.out.println("xxx");


		// is.close();
		socket.close();
	}
}
