package com.zzw;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
	static private final ImageIcon icon = new ImageIcon("./resource/title_cargo.jpg");

	ScrollPane spl;
	JPanel jpnl;
	ScrollPane spr;

	JLabel destination;
	JLabel mount;
	JLabel location;

	JTextField jtfDestination;
	JTextField jtfMount;
	JTextField jtfLocation;

	JButton load;
	JButton unload;

	JPanel[] jpl;


	public MainFrame(String title)
	{
		super(title);
		this.setBounds(500,200,900,600);
		this.setResizable(true);
		this.setIconImage(icon.getImage());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		load = new JButton("Load");
		unload = new JButton("Unload");

		destination = new JLabel("Destination:\t");
		mount = new JLabel("Mount:\t\t");
		location = new JLabel("Location:\t");

		jtfDestination = new JTextField("A", 5);
		jtfMount = new JTextField("5", 5);
		jtfLocation = new JTextField("A", 5);
		//todo: delete default text

		jpl = new JPanel[8];
		for (int i = 0; i < 8; i++)
			jpl[i] = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		jpl[1].add(destination);
		jpl[1].add(jtfDestination);
		jpl[2].add(mount);
		jpl[2].add(jtfMount);
		jpl[3].setLayout(new FlowLayout(FlowLayout.CENTER));
		jpl[3].add(load);
		jpl[5].add(location);
		jpl[5].add(jtfLocation);
		jpl[6].setLayout(new FlowLayout(FlowLayout.CENTER));
		jpl[6].add(unload);


		jpnl = new JPanel();
		jpnl.setLayout(new GridLayout(8, 1));
		for (int i = 0; i < 8; i++)
			jpnl.add(jpl[i]);

		spl = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
		spl.add(jpnl, BorderLayout.CENTER);
		spl.setPreferredSize(new Dimension(150, 0));

		this.add(spl, BorderLayout.WEST);

		this.validate();
	}

}
