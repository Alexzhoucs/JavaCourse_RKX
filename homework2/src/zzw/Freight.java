package zzw;

import javax.net.ssl.SSLContext;
import java.util.Scanner;
import java.lang.Exception;
import java.util.InputMismatchException;

public class Freight
{
	private int length;
	private Car firstCar;

	public Freight()
	{
		//char a[] = {'0','0','0','0'};
		this.firstCar = new Car(5);
		this.length = 5;
	}

	public static void main(String args[])
	{
		Freight f = new Freight();
		System.out.println(f);
		f.load();

	}

	public void load()
	{
		// input the destination and the number.
		boolean inputFlag = false;
		Scanner sc = new Scanner(System.in);
		char destination;
		int mount = 0;
		System.out.print("请输入货物目的地：");
		destination = sc.next().toCharArray()[0];
		System.out.println("读取目的地为：" + destination);        //继续IO

		System.out.print("请输入货物数量：");
		do
		{
			inputFlag = false;
			try
			{
				mount = sc.nextInt();
				if (mount <= 0||mount > 7) throw new Exception("输入错误：数量应在1-7间。");
			} catch (InputMismatchException e)
			{
				System.out.println("输入错误：请输入整数！");
				System.out.print("请重新输入：");
				sc = new Scanner(System.in);
				inputFlag = true;
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
				System.out.print("请重新输入：");
				inputFlag = true;
			}
		} while (inputFlag);
		System.out.println(mount);        //交互完成


	}

	@Override
	public String toString()        //need to be completed
	{
		String result = "Length: ";
		result += this.length;
		return result;
	}

	public class Car
	{
		char cargo[] = {'0', '0', '0', '0'};
		int weight = 0;        //装了几个东西

		Car next = null;


		public Car(char data[])
		{
			if (data.length <= 4)
			{
//				for (int i = 0; i < data.length; i++)
//					this.cargo[i] = data[i];
				System.arraycopy(data, 0, this.cargo, 0, data.length);
				this.weight = data.length;
			} else
			{
				this.weight = 4;
//				for (int i = 0; i < 4; i++)
//					this.cargo[i] = data[i];
				System.arraycopy(data, 0, this.cargo, 0, 4);
			}
		}

		public Car(int n)
		{
//			if(n <= 0)
//				return null;
//			this.next = Car(n - 1);
//			return this;
			if (n == 1) return;
			this.next = new Car(n - 1);
		}

	}
}
