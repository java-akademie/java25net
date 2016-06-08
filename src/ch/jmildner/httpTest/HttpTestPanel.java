package ch.jmildner.httpTest;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HttpTestPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	JButton b1 = new JButton("b1");
	JButton b2 = new JButton("b2");
	JButton b3 = new JButton("b3");
	JTextField tf1 = new JTextField("", 20);


	public HttpTestPanel()
	{
		makeTheLayout();
		addTheListener();
	}


	private void addTheListener()
	{
		b1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				b1Verarbeiten();
			}
		});

		b2.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				b2Verarbeiten();
			}
		});

		b3.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				b3Verarbeiten();
			}
		});
	}


	private void b1Verarbeiten()
	{
		tf1.setText("b1 gedrueckt");
	}


	private void b2Verarbeiten()
	{
		tf1.setText("b2 gedrueckt");
	}


	private void b3Verarbeiten()
	{
		tf1.setText("b3 gedrueckt");
	}


	private void makeTheLayout()
	{
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(4, 1));
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(tf1);
		setLayout(new BorderLayout());
		add(p1, BorderLayout.CENTER);
	}
}
