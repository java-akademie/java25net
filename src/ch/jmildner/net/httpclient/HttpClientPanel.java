package ch.jmildner.net.httpclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ch.jmb.tools.NetTools;
import ch.jmildner.tools.MyPanel;

public class HttpClientPanel extends MyPanel
{
	private static final long serialVersionUID = 1L;
	private JTextField tfUrl = new JTextField("localhost", 10);
	private JTextField tfAnz = new JTextField("", 5);
	private JButton send = new JButton("SEND");
	private JButton stopp = new JButton("STOPP");
	private JButton clear = new JButton("CLEAR");
	private JTextArea ta = new JTextArea("", 10, 80);


	public HttpClientPanel()
	{
		super();
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
		tfUrl.setText("");
		ta.setText("");
	}


	public JTextArea getTa()
	{
		return ta;
	}


	private void makeTheLayout()
	{
		addCaptionCenter("HTTP_CLIENT", 1, 1, 6);

		add(tfUrl, 10, 1, 2);
		add(tfAnz, 10, 3, 1);
		add(send, 20, 1, 1);
		add(stopp, 20, 2, 1);
		add(clear, 20, 3, 1);
		ta.setEditable(false);
		JScrollPane sp = new JScrollPane(ta);
		add(sp, 50, 1, 6, 1);

	}


	private void send() throws Exception
	{
		String url = tfUrl.getText();
		int anz;
		try
		{
			anz = Integer.parseInt(tfAnz.getText());
		}
		catch (Exception e)
		{
			anz = 1;
		}
		System.out.println(url + "/" + anz);

		while (anz > 0)
		{
			anz--;

			Socket socket;
			socket = new Socket(url, 8080);
			System.out.println(socket);

			BufferedReader br = NetTools.getBufferedReader(socket);
			PrintWriter pw = NetTools.getPrintWriter(socket);

			pw.println("GET / HTTP/1.0");
			pw.println("Content-Length: 100");
			pw.println("");
			pw.flush();

			String buff;

			while ((buff = br.readLine()) != null)
			{
				ta.append(buff + "\n");
			}

			br.close();
			pw.close();
			socket.close();
		}
	}


	private void stopp()
	{
		System.exit(0);
	}
}
