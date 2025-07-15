# 1. Java 程序设计概述

## 1.1 Java 程序设计平台

## 1.2 Java 关键术语

1.2.1 简单性
1.2.2 面向对象
1.2.3 分布式
1.2.4 健壮性
1.2.5 安全性
1.2.6 体系结构中立
1.2.7 可移植性
1.2.8 解释性
1.2.9 高性能
1.2.10 多线程
1.2.11 动态性

## 1.3 Java applet与Internet

## 1.4 Java 发展简史

## 1.5. Java常见误解

# 2. Java程序设计环境

## 2.1 Java开发工具包（JDK）

## 2.2 使用命令行工具
注释：在JDK11中，单个源文件不在需要javac命令，这个特性是为了支持以"shebang"(#!)行(#!/path/to/java)开头的shell脚本。

## 2.3 使用IDE

## 2.4 JShell

# 3. Java的基本程序设计结构
## 3.1 一个简答的Java应用程序
1. 类名：必须以大写字母开头，只能包含字母、数字和下划线。
## 3.2 注释
1. 单行注释：//
2. 多行注释：/* */，不能嵌套
3. 自动生成文档：/** */
## 3.3 数据类型
java是强类型语言，八种基本数据类型
1. 整型：
-  byte：8位有符号整数，1字节，-128~127。
-  short：16位有符号整数，2字节，-32768~32767。
-  int：32位有符号整数，4字节，-2147483648~2147483647。
-  long：64位有符号整数，8字节，-9223372036854775808~9223372036854775807。
ps：
    - 整型的范围与运行Java代码的平台没有关系。所以java可移植。
    - 长整型默认为long，可以加上L或者l。
    - 十六进制表示法：有一个前缀0x。
    - 八进制表示法：有一个前缀0，如07表示八进制7。
    - 二进制表示法：有一个前缀0b或者0B。
    - java没有无符号整数类型。

2. 浮点类型：
- float：单精度浮点数，4字节，后缀f或者F，-3.4028235E38~3.4028235E38。
- double：双精度浮点数，8字节，后缀d或者D，-1.7976931348623157E308~1.797693134
ps:
  - 有三个特殊的浮点数值表示溢出和出错情况：正无穷大(Double.INFINITY)，负无穷大(Double.NEGATIVA_INFINITY)，NaN(Double.NaN)。
  - eg: 一个正正数除以0，结果为正无穷大。计算0/0或者负数的平方根除以0，结果为NaN。

```java
class Main {
    public static void main(String[] args) {
        double x = Double.NaN;
        // is Never true 所有NaN都认为是不相同的
        if (x == Double.NaN) {
            System.out.println("x == NaN");
        }
        //但是可以使用函数判断
        if (Double.isNaN(x)) {
            System.out.println("x is NaN");
        }
        //x is NaN
    }
}
```

3. char
- char：16位无符号整数，2字节，0~65535。
- 码点：指与一个编码表中的字符对应的整数。在Unicode标准中，码点由16进制数表示并在前面加上U+。
  - Unicode的码点可以分成17个代码平面：
    - U+0000~U+FFFF:第一个代码平面，基本多语言平面，每个字符用16位表示，通常成为代码单元。
    - U+10000~U+10FFFF，包含各种辅助字符，而辅助字符编码为一对连续的代码单元。 
ps: 不要在程序中使用char来表示字符，除非确实需要处理UTF-16代码单元，应该使用String。

4. boolean
- 整型值和boolean之间不能相互转换。

## 3.4 变量与常量
1. 声明变量
变量名：只能有字母、数字、货币符合以及“标点连接符”组成，第一个字不能是数字。字母是指一种语言中表示字母的任何Unicode字符。
2. 初始化变量
ps: Java10开始，对于局部变量，可以从变量的初始值推断出他的类型，因此可以不声明变量类型。
3. 常量
- fianl 指示常量，表示这个变量只能被赋值一次，一旦被赋值就不能更改了。常量名使用全大写。
- static关键字表示静态的，被static修饰的属性是静态变量，属于类而不是某个对象。
4. 枚举类型
有限个命名值
## 3.5 字符串
1. 算数运算符
2. 数学函数与常量，Math类，如果需要结果严格精确，可以使用StrictMath类，他实现了可自由分发数据库，确保所有平台结果都是相同的。
3. 数值类型转换
4. 强制类型转换
   浮点型转为整型的时候，通过截断小数部分的浮点值转换为整型。如果想舍入(round)一个浮点数来得到最接近的正数，可以使用Math.round()方法，但该方法返回long。
5. 赋值
6. 自增自减：n++，先使用变量之后在+1，++n,先+1之后在使用变量。
7. 关系和boolean运算符:&& 和 || 安装短路的方式求值。
8. 条件运算符：condition？expression1：expression2
9. switch表达式：通过String判断
10. 位运算符
&：按位与，两个操作数都为1时，结果为1，否则为0。
|：按位或，两个操作数都为0时，结果为0，否则为1。
~：按位非，将操作数取反。-x=~x+1,~5=-6,因为按位取反之后获取的是补码。补码转换成原码：将补码的符号位不变，其余各位取反，末位加一。
^：按位异或，两个操作数不同时，结果为1，否则为0。
>>: 位移运算符，左移为<<，右移为>>。补符号位扩展：填充一定位数的符号位（非负数填充0，负数填充1）。
>>>: 位移无符号右移，将操作数向右移动指定位数，低位丢弃，高位补0。补零扩展：填充一定位数的0。 

## 3.6 字符串
1. 子串：String类下的substring(int beginIndex,int endIndex)实现,包含起始索引不包含结束索引的子串。
```java
class Main {
    public static void main(String[] args) {
        String greeting="Welcome";
        System.out.println(greeting.length());//7
        System.out.println(greeting.substring(0,7));// Welcome
    }
}
```
2. 拼接：
    使用+号 
    String类下的concat()方法。
    若需要把多个字符串放在一起，用一个界定符分隔，用String类下的join()方法。
    java11还引入repeat复制多个字符串。
```java
class Main {
    public static void main(String[] args) {
        String string1= "Hello ";
        String string2= "World";
        String string3= string1 + string2;
        System.out.println(string3);//Hello World
        String  string4= string3.concat("!!!");
        System.out.println(string4);//Hello World!!!
        String string5=String.join(" / ",string1,string2);
        System.out.println(string5);//Hello / World
        String string6=string1.repeat(4);
        System.out.println(string6);//Hello Hello Hello Hello
    }
}
```
3. 字符串不可变，编译器可以让字符串共享。
4. 检测字符串是否相等。
5. 空串：""，null。
6. 码点和代码单元
    length方法将返回采用UTF-16编码的字符串所需要的代码单元的个数，而codePointCount方法将返回采用UTF-16编码的码点个数。
    使用UTF-16编码表示字符U+1D546，需要两个代码单元，charAt(0)返回第一个代码单元，charAt(1)返回第二个代码单元。
7. String的API
8. 阅读联机API文档
9. 构建字符串：StringBuilder,StringBuffer效率不如StringBuilder，但是他允许采用多线程的方式添加或删除字符，StringBuffer线程安全。
```java
class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to ");
        sb.append("Java Programming!");
        //toString()方法将StringBuilder对象转换成String对象
        String s = sb.toString();
        System.out.println(s);
    }
}
```
10. 文本块：java15引入，以"""开始，以"""结束，中间可以包含任意字符，包括换行符。行尾\会把这一行与下一行连接起来。
## 3.7 输入与输出
1. 读取输入：Scanner类。
```java
import java.util.Objects;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        String a=in.next() ;
        System.out.println("Enter a string: "+a);
        double b=in.nextDouble();
        System.out.println("Enter a number: "+b);
    }
}
```
因为输入对所有人可见，所以Scanner类不适用于控制台读取密码。可以使用Console类。
```java
import java.io.Console;
import java.util.Objects;

class Main {
    public static void main(String[] args) {
// 如果Java程序要与windows下的cmd或者Linux下的Terminal交互，就可以使用这个Java Console类代劳。
// Java要与Console进行交互，不总是能得到可用的Java Console类的。
// 一个JVM是否有可用的Console，依赖于底层平台和JVM如何被调用。
// 如果JVM是在交互式命令行（比如Windows的cmd）中启动的，并且输入输出没有重定向到另外的地方，那么就可以得到一个可用的Console实例。
//但当使用Eclipse等IDE运行以上代码时Console中将会为null。
//表示Java程序无法获得Console实例，是因为JVM不是在命令行中被调用的，或者输入输出被重定向了。
//在Eclipse诸如类似的IDE工具中运行Console类。如果没有对Console实例判空操作，结果使用了该实例会抛出java.lang.NullPointerException异常。
        Console console = System.console();
        String username=console.readLine("Username: ");
        String password=new String(console.readPassword("Password: "));
        System.out.println(Objects.equals(username, "admin") && Objects.equals(password, "admin"));
    }
}
```
2. 格式化输出
3. 文件输入与输出
```java
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        //读取文件
        Scanner in=new Scanner(Path.of("D:\\AccessOK\\java\\java-basic\\src\\demo\\file.txt"), StandardCharsets.UTF_8);
        System.out.println(in.nextLine());
        in.close();
        //写入文件：PrintWriter覆盖文件内容，会new File
        PrintWriter out=new PrintWriter("D:\\AccessOK\\java\\java-basic\\src\\demo\\file.txt", StandardCharsets.UTF_8);
        out.println("Hello World!");
        out.close();
    }
}
```
## 3.8 控制流程
1. 块作用域，块作用域的变量不能在块外访问，且不能重复声明。
2. 条件语句：if(){}else{}
3. 循环：
- while(){}
```java
package Retirement;

import java.util.Scanner;

public class Retirement {
    public static void main(String[] args) {
        //攒够多少钱才能退休
        Scanner sc = new Scanner(System.in);
        System.out.print("How much money do you need to retire?: ");
        double goal = sc.nextDouble();
        System.out.print("How much money will you contribute every year?: ");
        double payment = sc.nextDouble();
        System.out.print("Interest rate in %: ");
        double interestRate = sc.nextDouble();
        double balance = 0;
        int years = 0;
        while (balance < goal){
            balance = (balance + payment) * (1 + interestRate/100);
            years++;
        }
        System.out.println("You can retire in " + years + " years.");
    }
}
```
- do while(){}
```java
package Retirement;

import java.util.Scanner;

public class Retirement2 {
    public static void main(String[] args) {
        //账户每年能攒下多少钱，钱到手之后在询问是否退休
        Scanner sc = new Scanner(System.in);
        System.out.print("How much money will you contribute every year? ");
        double payment = sc.nextDouble();
        System.out.println("Interest rate in %:");
        double interestRate = sc.nextDouble();
        double balance = 0;
        int years = 0;
        String input;
        do {
            balance = (balance + payment) * (1 + interestRate/100);
            years++;
            System.out.println("After " + years + " years you have " + balance);
            System.out.print("Do you want to make another payment? (y/n): ");
            input = sc.next();
        } while (input.equals("y"));
    }
}
```
4. 确定性循环：for(){},for(double x=0;x!=10;X+=0.1)可能永远不会结束，由于存在舍入误差，可能永远不能达到精确的最终值。
```java
package LotteryOdds;

import java.util.Scanner;

public class LotteryOdds {
    public static void main(String[] args) {
        //从n个数中选择k个数字，中将的概率
        Scanner in = new Scanner(System.in);
        System.out.print("How many numbers do you need to draw? ");
        int k = in.nextInt();
        System.out.print("What is the highest number you can draw? ");
        int n = in.nextInt();
        int lotteryOdds = 1;
        for (int i = 1; i <= k; i++)
            lotteryOdds = lotteryOdds * (n - i + 1) / i;
        System.out.println("Your odds are 1 in " + lotteryOdds + ". Good luck!");
    }
}
```
5. 多重选择：switch(expression){case value1:break;case value2:break;default:break;}，从选择值相匹配的case开始执行，直到遇到break或者default。
    如果忘记i俺家忘记break，那么case后面的代码会继续执行，直到遇到break或者default。
```java
//java14对格式做了修改
//以箭头-> 结束则没有直通行为，以冒号:结束则有直通行为
class Main {
    public static void main(String[] args) {
        int seasonCode=2;
        String seasonName=switch (seasonCode) {
            case 1 -> "Spring";
            case 2 -> "Summer";
            case 3 -> "Fall";
            case 4 -> "Winter";
            case 5 -> {
                System.out.println("Invalid Season Code");
                //如果需要执行代码块，则需要使用yield
                yield "Invalid Season Code";
            }
            default -> "Invalid Season Code";
        };
        System.out.println(seasonName);
        //枚举类型不用添加default
        enum Season {
            SPRING, SUMMER, FALL, WINTER
        }
        Season season=Season.SUMMER;
        switch (season) {
            case SPRING -> System.out.println("Spring");
            case SUMMER -> System.out.println("Summer");
            case FALL -> System.out.println("Fall");
            case WINTER -> System.out.println("Winter");
        }
    }
}
```
6. 控制流程的语句：带标签的break。标签必须放在你想跳出的最外层循环之前，并且必须紧跟一个冒号。执行带标签的break会跳转到带标签的语句块末尾。
## 3.9 大数 ： BigInteger类和BigDecimal类
## 3.10 数组
1. 数组声明，数组长度不要求是常量，但是一旦创建了数组，就不能在改变他的长度。
2. 访问数组元素：索引从0开始。
3. for each循环：for(variable : collection) { },collection是数组或实现了Iterable的对象。
4. 数组拷贝：引用变量拷贝，数组可以使用Arrays.copyOf(number[], int)。
5. 命令行参数
```java
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

class Main {
    //main函数接收一个字符串数组作为参数
    public static void main(String[] args) {
        if(args.length==0||args[0].equals("-h")){
            System.out.println("Helo");
        }
        else if(args[0].equals("-g")){
            System.out.println("Goodbye");
        }
        for(int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }
        System.out.println("!");
    }
}
```
6. 数组排序：对值类型排序可以使用Arrays.sort(number[]),对对象排序需要实现Comparable接口。
7. 多维数组：double[][] matrix = new double[3][4];
```java
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

class Main {
    //main函数接收一个字符串数组作为参数
    public static void main(String[] args) {
        final double STARTRATE = 10;
        final int NRATES = 6;
        final int NYEARS = 10;
        double[] interestRate = new double[NRATES];
        //定义单元行数据
        for (int i = 0; i < interestRate.length; i++) {
            interestRate[i] = (STARTRATE + i) / 100.0;
        }
        double[][] balances = new double[NYEARS][NRATES];
        //二维数组第一行数据填充
        for (int i = 0; i < interestRate.length; i++){
            balances[0][i] = 10000;
        }
        for (int i = 1; i < balances.length; i++) {
            for (int j = 0; j < balances[i].length; j++) {
                double oldBalance = balances[i-1][j];
                double interest = oldBalance * interestRate[j];
                balances[i][j] = oldBalance + interest;
            }
        }
        //输出一行的数据
        for (int i = 0; i < interestRate.length; i++){
            System.out.printf("%9.0f%%", interestRate[i] * 100);
        }
        for (double[] row : balances) {
            for (double b: row){
                System.out.printf("%10.2f", b);
            }
            System.out.println();
        }
    }
}
```
8. 不规则数组：二维数组可以看做是一维数组的数组，不规则数组即为数组的没有一行有不同的长度。