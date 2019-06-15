package com.zzw;

import javax.swing.*;

public class MainFrame extends JFrame
{
	static private final ImageIcon icon = new ImageIcon("./resource/title_cargo.jpg");

	public MainFrame(String title)
	{
		super(title);
		setBounds(500,200,900,600);
		setResizable(false);
		setIconImage(icon.getImage());
		setVisible(true);
	}

}
