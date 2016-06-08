package ch.jmildner.httpTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Vector;

import ch.java_akademie.tools.MyTools;

public class HttpClient
{
	// final static String urlPort = "jmildner.dnsalias.com:8089";
	final static String urlPort = "localhost:8088";
	final static String webroot = "j2eeSeminar";
	final static int MAX_B5 = 1000;
	final static int WAIT_FREQ = 100;
	final static int WAIT_TIME = 2500;
	final static Vector<Long> v5 = new Vector<Long>();


	static String ed(String s)
	{
		if (s.length() == 1)
			return "       " + s;
		if (s.length() == 2)
			return "      " + s;
		if (s.length() == 3)
			return "     " + s;
		if (s.length() == 4)
			return "    " + s;
		if (s.length() == 5)
			return "   " + s;
		return "  " + s;
	}


	public static void main(String[] args)
	{
		System.out.println("start testprogramm");

		thread5();

		System.out.println("---------------ende------------------");

		MyTools.pause();
		MyTools.pause();

		prot(5, v5);

		System.out.println();
	}


	private static void prot(int x, Vector<Long> v)
	{
		int z = 0;

		System.out.print("\nTime" + x);
		for (Iterator<Long> i = v.iterator(); i.hasNext();)
		{
			Long l = i.next();
			if (z++ % 10 == 0)
				System.out.println();
			System.out.print(ed(l.toString()));
		}
	}


	static void test5()
	{
		class Thread5 extends Thread
		{
			int th, id;


			Thread5(int th, int id)
			{
				this.th = th;
				this.id = id;
			}


			@Override
			public void run()
			{
				// System.out.println("... start ... " + th + "/" + id);
				// synchronized(o)
				{
					long runTime, t1, t0 = System.currentTimeMillis();
					String buff = "", data = "";
					URL url = null;
					URLConnection conn = null;
					OutputStreamWriter wr = null;
					BufferedReader br = null;
					try
					{
						url = new URL("http://" + urlPort + "/"
								+ webroot + "/roteTomate");
						conn = url.openConnection();
						conn.setDoOutput(true);
						wr = new OutputStreamWriter(
								conn.getOutputStream());
						wr.write(data);
						wr.flush();
						br = new BufferedReader(new InputStreamReader(
								conn.getInputStream()));
						data = "";
						while ((buff = br.readLine()) != null)
						{
							data = data + buff;
						}
					}
					catch (IOException e)
					{
						System.out.println("\n\n\n" + e + "\n\n\n");
					}
					t1 = System.currentTimeMillis();
					runTime = t1 - t0;
					v5.add(new Long(runTime));

					System.out.println("\n" + th + "/" + id + "..."
							+ data);
				}
			}
		}

		for (int i = 0; i < MAX_B5; i++)
		{
			if (i % WAIT_FREQ == 0)
			{
				MyTools.sleep(WAIT_TIME);
			}
			Thread5 th = new Thread5(5, i);
			th.start();
		}
	}


	private static void thread5()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				test5();
			}
		}.start();
	}
}
