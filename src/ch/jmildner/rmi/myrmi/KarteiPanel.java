package ch.jmildner.rmi.myrmi;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;

public class KarteiPanel
{
	class MyButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("Eintragen"))
			{
				try
				{
					msg.setText(k.recordEintragen(t1.getText(),
							t2.getText(), t3.getText()));
				}
				catch (Exception ex)
				{
					msg.setText("zugriff nicht moeglich");
					System.out.println("zugriff nicht moeglich");
					ex.printStackTrace();
				}
			}
			if (e.getActionCommand().equals("Abfragen"))
			{
				if (t1.getText().equals(""))
				{
					try
					{
						Datensatz ds = k.getRecord(t2.getText());
						if (ds != null)
						{
							t1.setText(ds.name);
							t2.setText(ds.nummer);
							t3.setText(ds.data);
							msg.setText("");
						}
						else
						{
							msg.setText("nicht gefunden");
						}
					}
					catch (Exception ex)
					{
						msg.setText("zugriff nicht moeglich");
						System.out.println("zugriff nicht moeglich");
						ex.printStackTrace();
					}
				}
				else
				{
					try
					{
						t2.setText(k.sucheNummer(t1.getText()));
						msg.setText("");
					}
					catch (Exception ex)
					{
						msg.setText("zugriff nicht moeglich");
						System.out.println("\nzugriff nicht moeglich");
						ex.printStackTrace();
					}
				}
			}
			if (e.getActionCommand().equals("Ruecksetzen"))
			{
				t1.setText("");
				t2.setText("");
				t3.setText("");
				msg.setText("rueckgesetzt");
				warten(2);
				anmelden();
			}
			if (e.getActionCommand().equals("Beenden"))
			{
				msg.setText("ende");
				warten(1);
				System.exit(1);
			}
		}
	}
	String server = null;
	static Kartei k = null;


	public static void main(String args[])
	{
		System.out.println("start karteipanel");
		Frame frame = new Frame();
		frame.add(new KarteiPanel("//localhost:11299/Kartei")
				.getKarteiPanel());
		frame.pack();
		frame.setVisible(true);
	}


	public static void schreiben(String s)
	{
		try
		{
			FileWriter resultsFile = new FileWriter(
					"karteiProtokoll.txt", true);
			PrintWriter toFile = new PrintWriter(resultsFile);
			toFile.println(s);
			toFile.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	Label l1 = new Label("name");
	Label l2 = new Label("nummer");
	Label l3 = new Label("data");
	TextField t1 = new TextField(40);
	TextField t2 = new TextField(40);
	TextField t3 = new TextField(40);
	Button b1 = new Button("Eintragen");
	Button b2 = new Button("Abfragen");
	Button b3 = new Button("Ruecksetzen");


	Button b4 = new Button("Beenden");


	TextField msg = new TextField(80);


	Panel karteiPanel = null;


	public KarteiPanel(String server)
	{
		this.server = server;
		makeTheLayout();
		addTheListener();
		anmelden();
	}


	void addTheListener()
	{
		MyButtonListener lis = new MyButtonListener();
		b1.addActionListener(lis);
		b2.addActionListener(lis);
		b3.addActionListener(lis);
		b4.addActionListener(lis);
	}


	void anmelden()
	{
		try
		{
			k = (Kartei) Naming.lookup(server);
			System.out.println("zugriff auf " + server + " ok!!!");
			msg.setText("zugriff auf " + server + " ok!!!");
		}
		catch (Exception e)
		{
			System.out.println("zugriff auf " + server
					+ " nicht moeglich");
			msg.setText("zugriff auf " + server + " nicht moeglich");
			warten(5);
		}
	}


	public Panel getKarteiPanel()
	{
		return karteiPanel;
	}


	void makeTheLayout()
	{
		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(5, 1));
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(5, 1));
		p2.add(t1);
		p2.add(t2);
		p2.add(t3);
		Panel p21 = new Panel();
		p21.setLayout(new BorderLayout());
		p21.add("Center", p2);
		p21.add("East", new Label(""));
		Panel p3 = new Panel();
		p3.setLayout(new GridLayout(5, 1));
		p3.add(b1);
		p3.add(b2);
		p3.add(b3);
		p3.add(b4);
		Panel p4 = new Panel();
		p4.setLayout(new BorderLayout());
		p4.add("West", p1);
		p4.add("Center", p21);
		p4.add("East", p3);
		p4.add("South", msg);
		karteiPanel = new Panel();
		karteiPanel.setLayout(new BorderLayout());
		karteiPanel.add("North", new Label(" "));
		karteiPanel.add("South", new Label(" "));
		karteiPanel.add("East", new Label(" "));
		karteiPanel.add("West", new Label(" "));
		karteiPanel.add("Center", p4);
		karteiPanel.setBackground(Color.yellow);
		karteiPanel.setForeground(Color.red);
		karteiPanel.setVisible(true);
	}


	void warten(int i)
	{
		try
		{
			Thread.sleep(i * 1000);
		}
		catch (Exception e)
		{
		}
	}
}
