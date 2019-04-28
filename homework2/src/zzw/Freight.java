package zzw;

import java.util.Scanner;

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
		Scanner sc = new Scanner(System.in);
		char destination;
		System.out.print("请输入货物目的地：");
		destination = sc.next().toCharArray()[0];
		System.out.println("读取目的地为：" + destination);        //继续IO


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
				for (int i = 0; i < data.length; i++)
					this.cargo[i] = data[i];
				this.weight = data.length;
			} else
			{
				this.weight = 4;
				for (int i = 0; i < 4; i++)
					this.cargo[i] = data[i];
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
			return;
		}

	}
}
