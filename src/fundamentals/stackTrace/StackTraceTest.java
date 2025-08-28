package stackTrace;

import java.util.Scanner;

public class StackTraceTest {
    public static int factorial(int n){
       System.out.println("factorial(" + n + ")");
       //StackWalker类会生成一个StackWalker.StackFrame实例流，其中每个实例分别描述一个栈帧。
       var walker = StackWalker.getInstance();
       //使用System.out的println函数迭代处理栈帧
       walker.forEach(System.out::println);
       int r;
       if (n <= 1) r = 1;
       //递归调用 n*(n-1)*(n-2)...*1
       else r = n * factorial(n - 1);
       System.out.println("return " + r);
       return r;
    }
    public static void main(String[] args) {
        //输入数字n
        try(var in = new Scanner(System.in)){
            System.out.print("Enter a nonnegative integer: ");
            int n = in.nextInt();
            factorial(n);
        }
    }
}
