package ch.jmildner.net.eswing;

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

public class EClientPanel extends MyPanel
{
	private static final long serialVersionUID = 1L;

	private JTextField zeile = new JTextField("", 20);
	private JTextField anz = new JTextField("", 5);

	private JButton send = new JButton("SEND");
	private JButton stopp = new JButton("STOPP");
	private JButton clear = new JButton("CLEAR");

	private JTextArea ta = new JTextArea("", 20, 20);


	public EClientPanel()
	{
		super(4, 10, true);
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
				System.exit(0);
			}
		});
		clear.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				zeile.setText("");
				anz.setText("");
				ta.setText("");
			}
		});
	}



//	public JTextArea getTa()
//	{
//		return ta;
//	}


	private void makeTheLayout()
	{
		addCaptionCenter("ECHO_CLIENT", 5, 1, 4);

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
		final int PORT=5555;
		
		Socket socket = new Socket("localhost", PORT);

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
			pw.println(z + "-" + i);
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


}
