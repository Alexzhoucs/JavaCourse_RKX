package com.zzw;

//import org.jetbrains.annotations.Contract;

import java.util.Scanner;
import java.lang.Exception;
import java.util.InputMismatchException;

public class Freight
{
	public int getLength()
	{
		return length;
	}

	public Car getFirstCar()
	{
		return firstCar;
	}

	private int length;
	private Car firstCar;

	Freight(int initialLength)
	{
		//char a[] = {'0','0','0','0'};
		this.firstCar = new Car(initialLength);
		this.length = initialLength;
	}

	void load()
	{
		// input the destination and the number.
		Scanner sc = new Scanner(System.in);
		boolean inputFlag;
		char destination;
		int mount = 0;
		System.out.println("开始装载操作！");
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
		} while (inputFlag);                    //交互完成
		//System.out.println(mount);

		if (mount >= 4)                            //处理整车负载
		{
			boolean findFlag = false;
			char cargo[] = {destination, destination, destination, destination};
			Car c = firstCar;
			while (!findFlag)                            //查找空车
			{
				if (c.getWeight() == 0)
				{
					try
					{
						c.add(cargo);
					} catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
					findFlag = true;
				}
				if (c.getNext() == null) break;
				c = c.getNext();
			}

			if (!findFlag)                                //无空车，新建车厢
			{
				Car newCar = new Car(cargo);
				c.setNext(newCar);
				length++;
			}
			mount -= 4;
		}

		// 零散放入
		try
		{
			Car c = firstCar;
			while (mount > 0)
			{
				if (c.getWeight() < 4)                //找到有空余车厢
				{
					if (mount <= (4 - c.getWeight()))
					{
						char cargo[] = new char[mount];
						for (int i = 0; i < mount; i++)
							cargo[i] = destination;
						c.add(cargo);
						mount = 0;
					} else
					{
						int sum = 4 - c.getWeight();
						mount -= sum;
						char cargo[] = new char[sum];
						for (int i = 0; i < sum; i++)
							cargo[i] = destination;
						c.add(cargo);
					}
				}
				if (c.getNext() == null&&mount > 0)            //若全满则增加车厢
				{
					char cargo[] = new char[mount];
					for (int i = 0; i < mount; i++)
						cargo[i] = destination;
					Car newCar = new Car(cargo);
					c.setNext(newCar);
					length++;
					mount = 0;
				}
				c = c.getNext();
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println("已成功装载货物！\n 目的地：\t" + destination);
	}

	void unload()
	{
		//IO
		Scanner sc = new Scanner(System.in);
		System.out.println("开始卸载操作！");
		System.out.print("请输入当前地址：");
		char destination = sc.next().toCharArray()[0];
		System.out.println("读取当前地址为：\t" + destination);

		//arithmetic
		Car c = firstCar;
		Car last = null;
		while (c != null)
		{
			if (c.sub(destination))
			{
				if (length > 1)        //最少留一节车厢
				{
					length--;
					try
					{
						if (c == firstCar) firstCar = c.getNext();
						else last.setNext(c.getNext());
					} catch (NullPointerException e)
					{
						e.printStackTrace();
					}
				}
				//last does not change
			} else
			{
				last = c;
			}
			c = c.getNext();
		}
	}


	@Override
	public String toString()        //need to be completed
	{
		int step = 0;
		Car c = firstCar;
		StringBuilder result = new StringBuilder("Freight length: \t");
		result.append(this.length);
		result.append('\n');
		while (c != null)
		{
			step++;
			result.append("\nCar ").append(step).append(" :\t");
			for (int i = 0; i < c.getWeight(); i++)
				result.append(c.cargo[i]).append('\t');
			c = c.getNext();
		}

		return result.toString();
	}


	class Car
	{
		private char cargo[] = {'0', '0', '0', '0'};
		private int weight = 0;        //装了几个东西

		private Car next = null;


		private Car(char data[])
		{
			if (data.length <= 4)
			{
				System.arraycopy(data, 0, this.cargo, 0, data.length);
				this.weight = data.length;
			} else
			{
				this.weight = 4;
				System.arraycopy(data, 0, this.cargo, 0, 4);
			}
			this.next = null;
		}


		String toString(int carNumber)
		{
			StringBuilder result = new StringBuilder("");
			result.append("Car ").append(carNumber).append(":").append("    ");
			for (int i = 0; i < weight; i++)
				result.append(cargo[i]).append('\t');
			return result.toString();

		}

		private Car(int n)
		{
			for (int i = 0; i < 4; i++)
				cargo[i] = '0';
			weight = 0;
			if (n == 1)
			{
				this.next = null;
				return;
			}
			this.next = new Car(n - 1);
		}

		//	@Contract(pure = true)
		private int getWeight()
		{
			return weight;
		}

		private void add(char[] data) throws Exception
		{
			// add new data to cargo; throws exception when the data is too big.
			if (data.length > (4 - this.weight)) {throw new Exception("ERROR in Freight - Car.add(char[]): 添加数据过大");}


//			for(int i = 0;i < data.length;i++)
//				cargo[weight + i] = data[i];
			System.arraycopy(data, 0, cargo, weight, data.length);

			weight += data.length;
		}

		private boolean sub(char destination)
		{
			//若删空则返回true
			int delete = 0;
			for (int i = 0; i < weight; i++)
				if (cargo[i] == destination)
				{
					delete++;
					cargo[i] = '0';
				}

			weight -= delete;
			if (weight == 0) return true;


			for (int i = 0; i < weight; i++)            // 整理cargo[] 使前 weight 个元素非空
				if (cargo[i] == '0') for (int j = weight; j < 4; j++)
					if (cargo[j] != '0')
					{
						cargo[i] = cargo[j];
						cargo[j] = '0';
						break;
					}

			return false;
		}

		//	@Contract(pure = true)
		Car getNext()
		{
			return next;
		}

		private void setNext(Car next)
		{
			this.next = next;
		}

	}
}
