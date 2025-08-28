package methods;

import java.lang.reflect.Method;

public class MethodTableTest {
    public static void main(String[] args) throws ReflectiveOperationException{
        // 调用静态方法square
        Method square = MethodTableTest.class.getMethod("square", double.class);
        // 调用静态方法sqrt
        Method sqrt = Math.class.getMethod("sqrt", double.class);
        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    public static double square(double x) {
        return x * x;
    }
    public static void printTable(double from, double to, int n, Method f) throws  ReflectiveOperationException{
        //打印数学函数的取值表
        System.out.println(f);
        double dx = (to - from) / n;
        for (double x = from; x <= to; x += dx){
            double y = (Double) f.invoke(null,x);
            System.out.printf("%10.4f | %10.4f%n", x, y);
        }
    }
}
