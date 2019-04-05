
public class Matrix
{
	private Fraction[][] f;
	private int order;

	public Matrix(long[][] a, int order)
	{
		this.order = order;
		f = new Fraction[order][order];
		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++)
				f[i][j] = new Fraction(a[i][j]);
	}

	public Matrix(Fraction[][] a, int order)
	{
		this.order = order;
		this.f = a;
	}

	public Matrix findInverse()
	{
		boolean intFlag = this.testInt();
		if (!intFlag)
		{
			System.out.println("ERROR in findAdjoint: the Matrix is not integer.");
			return null;
		}

		Matrix adjoint = this.findAdjoint();
		Determinant det = new Determinant(f);
		long temp = det.value();
//		System.out.println(temp);
		adjoint.multiFraction(new Fraction(1, det.value()));
		//	求行列式： new 行列式；行列式.value();

		return adjoint;
	}

	public Matrix findAdjoint()
	{
		boolean intFlag = this.testInt();
		if (!intFlag)
		{
			System.out.println("ERROR in findAdjoint: the Matrix is not integer.");
			return null;
		}
		//求伴随，一个一个数算代数余子式，调用行列式的class
		long[][] adj = new long[order][order];

		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++)
			{
				long[][] temp = new long[order - 1][order - 1];
				for (int m = 0, p = 0; p < order; m++, p++)        //temp赋值
				{
					if (p == i)
					{
						m--;
						continue;
					}
					for (int n = 0, q = 0; q < order; n++, q++)
					{
						if (q == j)
						{
							n--;
							continue;
						}
						temp[m][n] = f[p][q].getNumerator();
					}
				}
				adj[j][i] = (long) Math.pow((double) (-1), (double) (i + j));
				Determinant de = new Determinant(temp);

				adj[j][i] = adj[j][i] * de.value();            //new 行列式；行列式求值
			}

		return new Matrix(adj, order);
	}

	public int multiFraction(Fraction a)
	{
		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++)
				f[i][j] = Fraction.mul(f[i][j], a);
		return 0;
	}

	private boolean testInt()        //若均为整数则返回true
	{
//		boolean flag = true;
		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++)
				if (f[i][j].getDenominator() != 1) return false;

		return true;
	}

	public String toFile()
	{
		StringBuffer value = new StringBuffer("");
		for (int i = 0; i < order; i++)
		{
			for (int j = 0; j < order; j++)
				value.append(f[i][j].toString()).append("\t");
//				value = value + f[i][j].toString() + "\t";
			value.append("\r\n");
		}
		return value.toString();
	}

	@Override
	public String toString()
	{
		StringBuffer value = new StringBuffer("");
		for (int i = 0; i < order; i++)
		{
			for (int j = 0; j < order; j++)
				value.append(f[i][j].toString()).append("\t\t");
//				value = value + f[i][j].toString() + "\t";
			value.append("\n");
		}
		return value.toString();
	}
	//矩阵： 方法：求行列式、求逆矩阵、求伴随矩阵    构造方法：文件输入 + 检查（private）
	//main： 输入矩阵、检查并求出阶数、求逆矩阵
}

//行列式： 方法：求值
//分数：整数构造/分数构造  成员：求值/打印