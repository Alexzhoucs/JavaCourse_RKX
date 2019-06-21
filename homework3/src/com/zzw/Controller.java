package com.zzw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener
{
	//public Controller mainController;
	private MainFrame frame;
	private Freight freight;

	private final int initialLength = 5;

	public static void main(String[] args)
	{
		new Controller();
	}

	/*private void start()
	{
		mainController = new Controller();
	}*/

	public Controller()
	{
		freight = new Freight(initialLength);
		frame = new MainFrame("Freight Simulator", freight, this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("load"))
		{
			System.out.println("load");

		} else if (e.getActionCommand().equals("unload"))
		{
			System.out.println("unload");

		}

	}
}
