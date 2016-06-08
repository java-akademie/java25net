package ch.jmildner.net.chat;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ch.java_akademie.tools.MyPanel;

public class ChatClientFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	protected JTextArea output = new JTextArea(20, 50);
	protected JTextField input = new JTextField(50);


	public ChatClientFrame(String title)
	{
		super(title);

		MyPanel mp = new MyPanel(1, 40, false);

		mp.addTextArea(output, 10, 1, 1, 1);
		mp.add(input, 20, 1, 1);
		add(mp);
		pack();
		setVisible(true);
		output.setEditable(false);
		input.requestFocus();
	}
}
