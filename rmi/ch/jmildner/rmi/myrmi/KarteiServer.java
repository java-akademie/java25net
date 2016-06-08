package ch.jmildner.rmi.myrmi;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

class KarteiServer extends Frame
{
	class MyButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("start"))
			{
				statusFeld.setText(registrieren());
			}
			if (e.getActionCommand().equals("stop"))
			{
				statusFeld.setText(entfernen());
			}
			if (e.getActionCommand().equals("ende"))
			{
				statusFeld.setText(entfernen());
				warten(1);
				statusFeld.setText("ende");
				warten(1);
				System.exit(0);
			}
		}
	}

	private static final long serialVersionUID = 1L;


	public static void main(String args[])
	{
		new KarteiServer("Start und Stop des RMI-Servers");
	}

	KarteiImpl karteiImpl = null;
	String urlKartei = "rmi://localhost:11299/Kartei";
	int serverStatus = 0; // 0=nicht gestartet; 1=gestartet
	int gestartet = 1;
	int nichtGestartet = 0;
	Button startButton = new Button("start");
	Button stopButton = new Button("stop");
	Button endeButton = new Button("ende");


	TextField statusFeld = new TextField(80);


	TextField urlKarteiF = new TextField(40);


	KarteiServer(String s)
	{
		super(s);
		System.out.println("Konstruktor: KarteiServer");
		makeTheLayout();
		addTheListener();
		statusFeld.setText(registrieren());
	}


	void addTheListener()
	{
		MyButtonListener lis = new MyButtonListener();
		startButton.addActionListener(lis);
		stopButton.addActionListener(lis);
		endeButton.addActionListener(lis);
	}


	String entfernen()
	{
		String returnString;
		if (serverStatus == nichtGestartet)
		{
			returnString = "RMI-Server ist nicht gestartet";
		}
		else
		{
			try
			{
				Naming.unbind(urlKartei);
				returnString = "RMI-Server gestoppt " + urlKartei;
			}
			catch (Exception e)
			{
				returnString = "stop RMI-Server failed " + urlKartei;
			}
			serverStatus = nichtGestartet;
		}
		System.out.println(returnString);
		return returnString;
	}


	void makeTheLayout()
	{
		setBackground(Color.yellow);
		setForeground(Color.red);
		Panel panelN = new Panel();
		Panel panelS = new Panel();
		panelN.setLayout(new FlowLayout());
		urlKarteiF.setText(urlKartei);
		panelN.add(urlKarteiF);
		panelN.add(startButton);
		panelN.add(stopButton);
		panelN.add(endeButton);
		panelS.setLayout(new FlowLayout());
		panelS.add(statusFeld);
		setLayout(new BorderLayout());
		add("North", panelN);
		add("South", panelS);
		setSize(500, 300);
		setLocation(200, 100);
		pack();
		setVisible(true);
	}


	String registrieren()
	{
		String returnString;
		urlKartei = new String(urlKarteiF.getText());
		if (serverStatus == gestartet)
		{
			returnString = "RMI-Server ist schon gestartet";
		}
		else
		{
			try
			{
				karteiImpl = new KarteiImpl("online", statusFeld);
				try
				{
					Naming.rebind(urlKartei, karteiImpl);
					returnString = "RMI-Kartei-Server gestartet (rebind) "
							+ urlKartei;
				}
				catch (ConnectException e)
				{
					LocateRegistry.createRegistry(11299);
					Naming.rebind(urlKartei, karteiImpl);
					returnString = "RMI-Kartei-Server gestartet "
							+ "(createRegistry/rebind) " + urlKartei;
				}
				serverStatus = gestartet;
			}
			catch (Exception e)
			{
				returnString = "start RMI-Server failed " + urlKartei;
				System.out.println(e);
			}
		}
		System.out.println(returnString);
		return returnString;
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
