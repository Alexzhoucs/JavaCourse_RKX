import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
	public static void main(String args[])
	{
		int order;
		//输入和输出部分
		System.out.println("请将输入文件‘input.txt'放入运行根目录中。\n");
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入矩阵阶数：\t");
		order = sc.nextInt();
		sc.close();
//		System.out.println("order is " + order);
		long[][] data = new long[order][order];

		System.out.println("\n开始从文件中读取数据");

		File file = new File("input.txt");
		try
		{
			int i;
			Scanner f = new Scanner(file);
			for (i = 0; i < order&&f.hasNext(); i++)
			{
				String line = f.nextLine();
				String[] numbers = line.split("\\s{2,}|\t");
				for (int j = 0; j < numbers.length&&j < order; j++)
					data[i][j] = Long.parseLong(numbers[j]);
			}
			//TODO: 行元素不足
			if (i < order)
			{
				System.out.println("ERROR：文件行数比阶数小");
				f.close();
				return;
			}

			f.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("ERROR: cannot find file.");
		}

		Matrix a = new Matrix(data, order);

		System.out.println("读取原矩阵为：");
		System.out.println(a);
		System.out.println("计算得逆矩阵为：");
		System.out.println(a.findInverse());


		//输出到csv文件
		File output = new File("result.txt");

		try (PrintWriter out = new PrintWriter(output))
		{
			out.print(a.findInverse().toFile());
		} catch (FileNotFoundException e)
		{
			System.out.println("ERROR: cannot find file.");
		}

		System.out.println("已将结果输出到根目录文件：'result.txt'。");
	}
}
