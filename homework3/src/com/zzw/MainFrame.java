package com.zzw;

import com.zzw.Controller;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
	Freight f;
	Controller actionReceiver;

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


	public MainFrame(String title, Freight f, Controller actionReceiver)
	{
		super(title);
		this.setBounds(500,200,900,600);
		this.setResizable(true);
		this.setIconImage(icon.getImage());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.f = f;
		this.actionReceiver = actionReceiver;

		load = new JButton("Load");
		load.addActionListener(actionReceiver);
		load.setActionCommand("load");
		unload = new JButton("Unload");
		unload.addActionListener(actionReceiver);
		unload.setActionCommand("unload");

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

		//右侧布局
		PaintPanel ppr = new PaintPanel(f);
		spr = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		spr.add(ppr, BorderLayout.CENTER);
		this.add(spr, BorderLayout.CENTER);

		//todo: listener

		this.validate();            //显示组件
	}


	private class PaintPanel extends JPanel
	{
		private Freight f;
		private final int carWidth = 300;
		private final int carHeight = 40;
		private final int distance = 120;
		private final int carX = 180;

		private final int blankX = 320;
		private final int blankWidth = 20;
		private final int blankHeight = distance - carHeight - 20;

		private final Font stringFont = new Font("Times New Roman", Font.PLAIN, 20);


		private PaintPanel(Freight f)
		{
			this.f = f;
		}

		@Override
		public void paint(Graphics g)
		{
			this.setPreferredSize(new Dimension(660, distance * f.getLength()));

			super.paint(g);
			//g.fillRect(0,0,700,6000);

			System.out.println("f.length=" + f.getLength());

			Freight.Car thisCar = f.getFirstCar();
			for (int i = 0; i < f.getLength(); i++)
			{
				g.setColor(Color.BLUE);
				g.fillRect(carX, 20 + i * distance, carWidth, carHeight);

				g.setColor(Color.WHITE);
				g.setFont(stringFont);
				System.out.println(thisCar.toString(i + 1));
				g.drawString(thisCar.toString(i + 1), carX + 20, 45 + i * distance);
				thisCar = thisCar.getNext();

				if (i < f.getLength() - 1)
				{
					g.setColor(Color.BLACK);
					g.fillRect(blankX, 30 + i * distance + carHeight, blankWidth, blankHeight);
				}
			}

			this.revalidate();
		}

	}

}
