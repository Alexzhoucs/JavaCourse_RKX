package zzw;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Controller
{
	private final int initialLength = 5;
	private final String menuOutput = "\n请输入命令代码进行操作：\n--0\t查看货车状态\n--1\t装载货物\n--2\t卸载货物\n--9\t退出程序\n请输入：";

	private Freight f;

	public static void main(String args[])
	{
		Controller c = new Controller();
		c.start();

	}

	private Controller()
	{
		f = new Freight(initialLength);
	}

	private void start()
	{
		boolean onOff = true;            //若为true则开
		boolean inputFlag;                //若为true则为输入类型有误
		System.out.println("欢迎使用货车模拟程序\n\t\tby Alex Zhou");


		while (onOff)
		{
			System.out.print(menuOutput);
			int command = 0;
			do
			{
				Scanner sc = new Scanner(System.in);
				inputFlag = false;
				try
				{
					command = sc.nextInt();
				} catch (InputMismatchException e)
				{
					System.out.print("\n输入数据类型有误，请输入整数类型命令！\n 请重新输入：");
					inputFlag = true;
				}
			} while (inputFlag);

			switch (command)
			{
				case 0:
					System.out.println("\n读取货车状态中......\n");
					System.out.println(f);
					break;

				case 1:
					System.out.println("");
					f.load();
					break;

				case 2:
					System.out.println("");
					f.unload();
					break;

				case 9:
					System.out.println("\n感谢使用，再见！");
					onOff = false;
					break;

				default:
					System.out.println("\n未定义命令，请在下列菜单中选择命令输入！");
					break;
			}
		}
	}
}
