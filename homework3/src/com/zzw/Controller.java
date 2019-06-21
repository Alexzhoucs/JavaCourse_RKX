package com.zzw;

public class Controller
{
	private MainFrame frame;
	private Freight freight;

	private final int initialLength = 5;

	public static void main(String[] args)
	{
		Controller c = new Controller();
	}

	public Controller()
	{
		freight = new Freight(initialLength);
		frame = new MainFrame("Freight Simulator", freight);


	}
}
