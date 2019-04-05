public class Fraction
{
	private long denominator;
	private long numerator;

	public long getDenominator()
	{
		return denominator;
	}

	public long getNumerator()
	{
		return numerator;
	}

	public Fraction(long numerator)
	{
		this.numerator = numerator;
		this.denominator = 1;
	}

	public Fraction(long numerator, long denominator)
	{
		this.denominator = denominator;
		this.numerator = numerator;
		int err = this.check();
		//System.out.println("New Fraction Checked; ERROR Code: " + err);
	}

	public static Fraction add(Fraction a, Fraction b)
	{
		long denominator = a.denominator * b.denominator;
		long numerator = a.denominator * b.numerator + b.denominator * a.numerator;
		return new Fraction(numerator, denominator);
	}

	public static Fraction mul(Fraction a, Fraction b)
	{
		return new Fraction(a.numerator * b.numerator, a.denominator * b.denominator);
	}

	public static Fraction sub(Fraction a, Fraction b)
	{
		Fraction minus = new Fraction(-1);
		Fraction c = Fraction.mul(minus, b);
		return Fraction.add(a, c);
	}

	private int check()
	{
		if (denominator == 1) return 0;

		//分母不为0
		if (denominator == 0)
		{
			System.out.println("ERROR: The denominator should not be 0.");
			return -1;
		}

		//分子能整除分母
		if (numerator % denominator == 0)
		{
			numerator = numerator / denominator;
			denominator = 1;
		}

		//正负号调节
		if (denominator < 0)
		{
			numerator *= (-1);
			denominator *= (-1);
		}

		//约分
		long gcd = Math.max(Math.abs(numerator), Math.abs(denominator));
		long n = Math.min(Math.abs(numerator), Math.abs(denominator));
		long r;
		while (n != 0)
		{
			r = gcd % n;
			gcd = n;
			n = r;
		}
		if (gcd > 1)
		{
			numerator = numerator / gcd;
			denominator = denominator / gcd;
		}
		return 0;
	}

	public double value()
	{
		return ((double) numerator / (double) denominator);
	}

	@Override
	public String toString()
	{
		String value = "";
		if (numerator >= 0) value += " ";
		if (denominator == 1) return value + numerator;
		else return value + numerator + "/" + denominator;
	}
}
