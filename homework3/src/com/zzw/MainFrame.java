package com.zzw;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
	static private final ImageIcon icon = new ImageIcon("./resource/title_cargo.jpg");

	private ScrollPane spl;
	private JPanel jpnl;
	private ScrollPane spr;

	private JLabel destination = new JLabel("Destination:");
	private JLabel mount = new JLabel("Mount         :");
	private JLabel location = new JLabel("Location    :");

	private JTextField jtfDestination;
	private JTextField jtfMount;
	private JTextField jtfLocation;

	private JButton load;
	private JButton unload;

	private JPanel[] jpl;


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

//		destination = new JLabel("Destination:");
//		mount = new JLabel("Mount:");
//		location = new JLabel("Location:");

		jtfDestination = new JTextField("A", 5);
		jtfMount = new JTextField("5", 5);
		jtfLocation = new JTextField("A", 5);
		//todo: delete default text

		jpl = new JPanel[9];
		for (int i = 0; i < 9; i++)
			jpl[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));

		jpl[1].add(destination);
		jpl[1].add(jtfDestination);
		jpl[2].add(mount);
		jpl[2].add(jtfMount);
		//jpl[3].setLayout(new FlowLayout(FlowLayout.CENTER));
		jpl[3].add(load);
		jpl[5].add(location);
		jpl[5].add(jtfLocation);
		//jpl[6].setLayout(new FlowLayout(FlowLayout.CENTER));
		jpl[6].add(unload);


		jpnl = new JPanel();
		jpnl.setLayout(new GridLayout(9, 1));
		for (int i = 0; i < 9; i++)
			jpnl.add(jpl[i]);

//		JPanel jpnlr = new JPanel();
//		JLabel jl1 = new JLabel("");

		spl = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
		spl.add(jpnl, BorderLayout.CENTER);
		spl.setPreferredSize(new Dimension(200, 0));                //设置WEST区域宽度

		this.add(spl, BorderLayout.WEST);
		//左侧完成

		//todo: right side and listener

		this.validate();            //显示组件
	}



}
