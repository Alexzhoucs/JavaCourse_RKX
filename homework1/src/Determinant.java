public class Determinant
{
	private long[][] data;
	private int order;

	public Determinant(long[][] data)
	{
//		this.data = new long[data.length][data.length];
		this.data = data;
		this.order = data.length;
	}

	public Determinant(Fraction[][] f)
	{
		this.order = f.length;
		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++)
				this.data[i][j] = f[i][j].getNumerator();
	}

	public long value()
	{
		if (order == 1) return data[0][0];

		long value = 0;

		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++)
			{
				long[][] cofactor = new long[order - 1][order - 1];
				for (int m = 0, p = 0; p < order; m++, p++)        //cofactor 赋值
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
						cofactor[m][n] = data[p][q];
					}
				}
				Determinant A = new Determinant(cofactor);

				value += data[i][j] * (A.value() * (long) Math.pow((double) (-1), (double) (i + j)));
			}

		return value;
	}

}
