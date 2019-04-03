public class Fraction
{
    private long denominator;
    private long numerator;

    public Fraction(long numerator)
    {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Fraction(long numerator, long denominator)
    {
        this.denominator = denominator;
        this.numerator = numerator;
        this.check();
    }

    private int check()
    {
        if(denominator ==1)
            return 0;

        //分母不为0
        if(denominator == 0)
        {
            System.out.println("ERROR: The denominator should not be 0.");
            return -1;
        }

        //分子能整除分母
        if(numerator % denominator == 0)
        {
            numerator = numerator / denominator;
            denominator = 1;
        }

        //正负号调节
        if(denominator < 0)
        {
            numerator *= (-1);
            denominator *= (-1);
        }

        //约分
        long gcd = Math.max(Math.abs(numerator), Math.abs(denominator));
        long n = Math.min(Math.abs(numerator), Math.abs(denominator));
        long r;
        while( n != 0)
        {
            r = gcd % n;
            gcd = n;
            n = r;
        }
        if(gcd > 1)
        {
            numerator = numerator / gcd;
            denominator = denominator / gcd;
        }
        return 0;
    }


    public String toString()
    {

    }

    public static Fraction add(Fraction a,Fraction b)
    {

    }



}
