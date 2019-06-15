package com.zzw;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
	static private final ImageIcon icon = new ImageIcon("./resource/title_cargo.jpg");
	static ScrollPane sp;


	public MainFrame(String title)
	{
		super(title);
		this.setBounds(500,200,900,600);
		this.setResizable(true);
		this.setIconImage(icon.getImage());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		sp = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
		//todo: set sp

	}

}
