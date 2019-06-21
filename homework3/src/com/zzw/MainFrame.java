package com.zzw;

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
	private PaintPanel ppr;

	private JLabel destination = new JLabel("Destination:");
	private JLabel mount = new JLabel("Mount         :");
	private JLabel location = new JLabel("Location    :");

	private JTextField jtfDestination;
	private JTextField jtfMount;
	private JTextField jtfLocation;

	public void setJtfNull()
	{
		jtfDestination.setText("");
		jtfMount.setText("");
		jtfLocation.setText("");
	}


	public char getCargoDestination()
	{
		return jtfDestination.getText().toCharArray()[0];
	}

	public int getMount()
	{
		int result;
		String mount = jtfMount.getText();

		//处理输入格式错误
		try
		{
			result = Integer.parseInt(mount);
		} catch (NumberFormatException e)
		{
			String title = "WRONG INPUT TYPE";
			String content = "INPUT ERROR: You should input an integer.\nPlease input again.";
			JOptionPane.showMessageDialog(null, content, title, JOptionPane.WARNING_MESSAGE);
			return -1;
		}

		//处理输入数值错误
		try
		{
			if (result <= 0||result > 7)
				throw new Exception("INPUT ERROR: The Mount should be greater than 0 and " + "smaller than 8.\nPlease input again.");
		} catch (Exception e)
		{
			String title = "WRONG INPUT VALUE";
			JOptionPane.showMessageDialog(null, e.getMessage(), title, JOptionPane.WARNING_MESSAGE);
			return -2;
		}
		System.out.println("Mount: " + result);
		return result;
	}

	public char getCargoLocation()
	{
		return jtfLocation.getText().toCharArray()[0];
	}

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

		jtfDestination = new JTextField("", 5);
		jtfMount = new JTextField("", 5);
		jtfLocation = new JTextField("", 5);

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
		ppr = new PaintPanel(f);
		spr = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		spr.add(ppr, BorderLayout.CENTER);
		this.add(spr, BorderLayout.CENTER);


		this.validate();            //显示组件
	}

	@Override
	public void validate()
	{
		super.validate();
		if (ppr != null) ppr.repaint();
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
