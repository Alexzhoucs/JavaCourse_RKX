import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Matrix
{
    Fraction[] f;


    public static void main(String args[])
    {
//        Fraction a = new Fraction(3);
//        System.out.println(a);
//        Fraction b = new Fraction(2,3);
//        System.out.println(a +"\t" + b + "\t" + a.value() + "\t" + b.value());
//        System.out.println(Fraction.add(a,b));
//        System.out.println(Fraction.sub(a,b));
//        System.out.println(Fraction.sub(b,a));

        Scanner sc = new Scanner(System.in);
        System.out.println("Input an integer.");
        int a = sc.nextInt();
        System.out.println(a);

        //输入方法

        File file = new File("1.txt");
        try
        {
            Scanner f = new Scanner(file);
            while (f.hasNext())
            {
                int data = f.nextInt();
                System.out.println(data);
            }
            f.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("ERROR: cannot find file.");
        }


    }
    //矩阵： 方法：求行列式、求逆矩阵、求伴随矩阵    构造方法：文件输入 + 检查（private）
    //main： 输入矩阵、检查并求出阶数、求逆矩阵
}

//行列式： 方法：求值
//分数：整数构造/分数构造  成员：求值/打印