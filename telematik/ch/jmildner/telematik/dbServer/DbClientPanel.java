package ch.jmildner.telematik.dbServer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import ch.java_akademie.tools.MyTools2;

public class DbClientPanel extends Panel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	String buff;
	Panel up = new Panel();
	Label l1 = new Label("Kennnung");
	Label l2 = new Label("Name");
	TextField tf1 = new TextField(10);
	TextField tf2 = new TextField(40);
	Button b2 = new Button("Abfragen");
	Button b4 = new Button("Beenden");
	TextArea ta = new TextArea(16, 70);


	public DbClientPanel() throws UnknownHostException, IOException
	{
		makeTheNetwork();
		makeTheLayout();
		addTheListener();
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		ta.append(e + "\n");
		if (e.getSource() == b2)
		{
			int i;
			buff = tf1.getText();
			try
			{
				i = Integer.parseInt(buff);
			}
			catch (Exception f)
			{
				i = 1111;
				tf1.setText(i + "");
			}
			try
			{
				buff = dbZugriff(i);
			}
			catch (IOException e1)
			{
				buff = e1.getMessage();
			}
			tf2.setText(buff);
			if (i == 0)
				System.exit(0);
		}
		if (e.getSource() == b4)
		{
			try
			{
				buff = dbZugriff(0);
			}
			catch (IOException e1)
			{
				buff = e1.getMessage();
			}
			tf2.setText(buff);
			System.exit(0);
		}
	}


	private void addTheListener()
	{
		b2.addActionListener(this);
		b4.addActionListener(this);
	}


	public String dbZugriff(int i) throws IOException
	{
		MyTools2.writeLine(pw, i + "");
		MyTools2.flush(pw);
		String buff = MyTools2.readLine(br);
		return buff;
	}


	private void makeTheLayout()
	{
		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(5, 1));
		p1.add(l1);
		p1.add(l2);
		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(5, 1));
		p2.add(tf1);
		p2.add(tf2);
		Panel p3 = new Panel();
		p3.setLayout(new GridLayout(4, 1));
		p3.add(b2);
		p3.add(b4);
		Panel p4 = new Panel();
		p4.add(ta);
		up.setLayout(new BorderLayout(8, 8));
		up.add("West", p1);
		up.add("Center", p2);
		up.add("East", p3);
		up.add("South", p4);
		setLayout(new BorderLayout());
		add("North", new Label(""));
		add("South", new Label(""));
		add("East", new Label(""));
		add("West", new Label(""));
		add("Center", up);
	}


	private void makeTheNetwork() throws UnknownHostException, IOException
	{
		socket = MyTools2.getSocket("localhost", 8088);
		br = MyTools2.getBufferedReader(socket);
		pw = MyTools2.getPrintWriter(socket);
	}
}
