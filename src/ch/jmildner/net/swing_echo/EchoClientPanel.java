package ch.jmildner.net.swing_echo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ch.jmb.tools.NetTools;
import ch.jmildner.tools.MyPanel;

public class EchoClientPanel extends MyPanel
{
	private int id;

	private static final long serialVersionUID = 1L;
	private JTextField zeile = new JTextField("", 20);
	private JTextField anz = new JTextField("", 5);
	private JButton send = new JButton("SEND");
	private JButton stopp = new JButton("STOPP");
	private JButton clear = new JButton("CLEAR");
	private JTextArea ta = new JTextArea("", 20, 20);


	public EchoClientPanel(int id)
	{
		super();
		this.id = id;
		makeTheLayout();
		addTheListener();
	}


	private void addTheListener()
	{
		send.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					send();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		stopp.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				stopp();

			}
		});
		clear.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				clear();

			}
		});
	}


	private void clear()
	{
		zeile.setText("");
		anz.setText("");
		ta.setText("");
	}


	public JTextArea getTa()
	{
		return ta;
	}


	private void makeTheLayout()
	{
		addCaptionCenter("ECHO_CLIENT", 5, 1, 3);

		add(zeile, 10, 1, 3);
		add(anz, 10, 4, 1);
		add(send, 20, 1, 1);
		add(stopp, 20, 2, 1);
		add(clear, 20, 3, 1);
		ta.setEditable(false);
		addTextArea(ta, 30, 1, 4, 1);
	}


	private void send() throws Exception
	{
		Socket socket = new Socket("localhost", 5555);

		BufferedReader br = NetTools.getBufferedReader(socket);
		PrintWriter pw = NetTools.getPrintWriter(socket);

		String buff;
		String z = zeile.getText();
		int a = 1;
		try
		{
			a = Integer.parseInt(anz.getText());
		}
		catch (Exception e)
		{
		}

		for (int i = 1; i <= a; i++)
		{
			pw.println(id + " " + z + "-" + i);
			pw.flush();
			buff = br.readLine();
			ta.append(buff + "\n");
		}
		pw.println("$$$");
		pw.flush();
		br.close();
		pw.close();
		socket.close();
	}


	private void stopp()
	{
		System.exit(0);
	}
}
