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
```java
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        //定义一个不规则数组
        final int MAX=10;
        int[][] odds=new int[MAX+1][];
        for(int i=0;i<=MAX;i++){
            odds[i]=new int[i+1];
        }
    }
}
```
# 对象与类

## 面向对象程序设计概述

1. 类
2. 对象
3. 识别类：名词方便识别的类
4. 类之间的关系：依赖、聚合、继承

## 使用预定义类

1. 对象与对象变量：构造器与类同名。
2. Java类库中的LocalDate类：日历表示法。创建对象：LocalDate.now()//静态工厂方法
3. 更改器方法与访问器方法：更改器方法原对象改变，访问器方法只根据原对象数据创建新对象并返回。
```java
package CalendarTest;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalenderTest {
    public static void main(String[] args) {
        //显示当前月的日历
        LocalDate date = LocalDate.now();
        //获取当前月份和日期
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();
        //将Date设置为这个月的第一天，并获取星期，
        //minusDays()方法返回一个LocalDate对象，该对象表示当前日期减去指定天数
        date = date.minusDays(today-1);
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue(); //1=Monday, 7=Sunday

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < value; i++)
            System.out.print("    ");
        //显示当前月
        while (date.getMonthValue() == month){
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today){
                System.out.print("*");
            }else System.out.print(" ");
            //date 往后一天，直到当前月结束
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1){
                System.out.println();
            }
        }
    }
}
```

## 自定义类

1. Employee类：一个源文件中只能有一个公共类，但是可以有任意数量的非公共类。
2. 使用多个源文件
3. 剖析Employee类
4. 从构造器开始：构造器总是结合new操作符来调用，构造器没有返回值，Java对象都是在堆中构造的。
5. 用var声明局部变量(Java10引入)：如果局部变量可以根据局部变量的初始值推导出他们的类型，可以使用var声明局部变量。
6. 使用null引用：基本类型不会为null。引用类型可以赋值为null。
7. 隐式参数和显示参数：隐式参数是方法调用时，方法参数列表中的第一个参数，可以是实例类型的对象，this。显示参数是方法调用时，方法参数列表中的实际参数。
8. 封装的优点：set/get方法封装字段可以保护字段不受外界的破坏，可以在方法里做特殊处理。
9. 基于类的访问权限：一个类的方法可以访问这个类的所有对象的私有数据。
10. 私有方法:private。
11. final实例字段：必须在狗仔对象时初始化，并且不能再修改这个字段，所以final修饰的字段没有set方法。对象引用也不会再指向另一个不同的对象。

## 静态字段和静态方法

1. 静态字段：static，每个静态字段只有一个副本，可以认为静态字段属于类，而不属于单个对象。该类的所有对象共享该变量。
2. 静态常量：static final。静态常量必须被初始化，并且不能被修改。
3. 静态方法：static。静态方法只能访问静态字段。属于类不属于对象，所以静态方法没有隐士参数。
4. 工厂方法：使用静态工厂方法构造对象。创建静态工厂类创建对象。
5. main方法：main方法不对任何对象进行操作，启动程序时还没有任何对象。将执行静态main方法，并构造城西所需要的对象。

## 方法参数

Java总时采用按值调用，方法会得到所有参数值的一个副本。改变参数值不会改变实际参数的值。对象引用也会复制一个引用值传进去。

## 对象构造

1. 重载：类可以有多个构造器，相同的方法名不同的参数。
2. 默认字段初始化：构造器中没有显示的为一个字段设置初始值，那么Java会为这个字段分配一个默认值。数值：0，布尔值：false；对象引用：null。
3. 无参的构造器：如果没有写构造函数，java会默认提供一个。
4. 显式字段初始化：重载类的构造器方法。
5. 参数名：构造器参数名可以与字段名相同，但是不建议这样做。可以使用this指定。
6. 调用另一个构造器：构造器可以调用其他构造器。
7. 初始化块：初始化实例字段可以在构造器中设置值，也可以在声明中赋值。还可以构造初始化代码块。首先运行初始化块，然后才运行构造器的主体部分。
8. 对象析构与finalize方法：防止一些清理代码，当对象不在使用可能需要执行这些清理代码。但是Java会完成自动的垃圾回收，所以Java不支持析构器。

## 记录

1. 记录概念：根据record关键字自动生成set，get，equals，hashCode，toString方法。
2. 构造器：标准、自定义和简洁。所有的记录字段默认是fianl。

## 包

1. 包名：为了保证包名的绝对唯一性，可以使用因特网域名以逆序的形式作为报名。然后对于不同的项目使用子包。
2. 类的导入：import
3. 静态导入：import static，导入静态方法和字段，而不必加类名前缀。
4. 在包中增加类：将报名放在源文件开头，package。如果包与目录不匹配，虚拟机就找不到这些类。
5. 包访问：default 访问权限，包中所有的类都可以访问该变量。
6. 类路径：所有包含类文件的路径集合。类路径所列出的目录和归档文件时搜寻类的起始点。类路径包括基目录、当前目录、Jar文件。
7. 设置类路径： java --classpath /PATH/TO/CLASS

## JAR文件

1. 创建JAR文件：jar cvf jar-file input-file-specification
2. 清单文件：MANIFEST.MF，位于META-INF目录下。jar cfm/ufm MANIFEST.MF jar-file input-file-specification
3. 可执行JAR文件：jar 命令可以使用e指定程序的入口。或者通过Main-class设置主类。
4. 多版本Jar文件：特定于版本的类文件放在META-INF/versions目录下。构建多版本JAR文件，可以使用-X，对应每个版本要切换到一个不同的类文件目录。面向不同版本编译是，要使用--release和-指定版本和输出目录。
```bash
jar cf MyProject.jar -C bin/8 . -C MyProject/bin/9 --release 9 Application.class
```
5. 关于命令行选项的说明：

## 文档注释

1. 注释的插入：javadoc可以有源文件生成html文档。/**....*/
2. 类注释：类注释必须放在import之后，clss定义之前。
3. 方法注释：@param 参数描述，@return 返回值描述，@throws 抛出异常描述。
4. 字段注释：
5. 通用注释：@author 作者，@version 版本号，@since 从哪个版本开始使用。@see 参考的类或方法。@link 链接。
6. 包注释：在每个包目录中添加一个单独的文件。package-info.java或package.html。
7. 注释提取
```bash
javadoc -d docs -sourcepath src -subpackages com.example.demo
```

## 类设计技巧

- 保证数据私有
- 一定要初始化数据
- 不要在类中使用过多的基本类型
- 不是所有字段都需要单独的访问器和更改器
- 分解过多职责的类

# 继承

## 类、超类、子类

1. 定义子类：extends继承。
2. 覆盖方法：父类方法名相同，参数相同，返回值相同。super不是一个对象的引用，不能将super赋给另一个对象，只是一个指示编译器调用超类方法的关键字。
3. 子类构造器：
```text
由于子类的构造器不能访问父类的私有字段，所以必须通过一个构造器(super)来初始化这些私有字段。
如果构造子类对象时没有显式调用超类构造器，编译器也会调用超类的无参构造器。
调用构造器的语句只能作为另一个构造器的第一天语句出现。构造器参数可以传递给当前类(this)的另一个构造器，也可以传递给超类的构造器
多态：一个对象变量可以指示多种实际类型。
动态绑定：在运行时能够自动的选择适当的方法，引用的哪个类型就调用引用类型的方法。
```
4. 继承层次结构
5. 多态：需要超类对象的任何地方都可以使用子类对象替换。
6. 理解方法调用：
7. 阻止继承: final类和方法。如果一个方法没有被覆盖并且很短，编译器会对其进行优化处理，成为内联。
8. 强制类型转换：只能在继承层次结构内进行强制类型转换。在将超类强制转换成子类之前，应该使用instanceof进行检查。
9. instanceof模式匹配：强制类型转换之前判断是否是子类。
10. 受保护访问：继承关系中的类可以访问protected成员。

## Object：所有类的超类

1. Object类型的变量：Object是所有类的超类，可以使用Object类型的变量引用任何类型的对象。
2. equals方法：Object类中的equals方法用于检测一个对象是否等于另一个对象（两个对象引用是否相同）。在子类中定义equals方法是，首先调用超类的equals。
3. 相等测试与继承：在Java标准库中包含150多个equls方法的实现，包括instanceof检测，调用getclass检测等。
```text
//equals方法编写建议：
1. 将显示参数命名为otherObject，稍后需要将它强制转换成另一个other的变量。
2. 检测this与otherObject是否为同一对象。
3. 检测otherObject是否为null，如果为null，则返回false。
4. 比较this和otherObject的类。如果equels的语义可以在子类中改变，就使用getClass()方法检测，如果所有的子类都有相同的相等性语义，就使用instanceof关键字检测。
5. 使用==比较基本类型字段，使用Objects.equals()方法比较对象字段。如果都匹配，则返回true。
```
4. hashCode方法：
```text
由于hashCode方法定义在Object类中，因此每个对象都有默认的散列码，其值有对象的存储地址得出。
String类型的散列码是由内容导出的，Stringbuilder构建的对象会根据存储地址得出散列码。
如果x.equals(y)=true,则x.hashCode与y.hashCode相等。数组散列码有数组元素的散列码组成。
record(记录)类型会自动提供一个hashCode方法。
```
5. toString方法：Object类中定义的toString方法，会打印对象的类名和散列码。

## 泛型数组列表

1. 声明数组列表：使用var声明ArrayList时，不要使用菱形语法。因为菱形处的泛型参数类型会根据变量类型自动推导，var类型无法确定泛型参数类型。当数组内部以及满了，编译器会自动创建一个更大的数组，并拷贝全部数据到新的数组中。
2. 访问数组列表元素：使用get方法获取数组列表元素。
3. 类型化与原始数组列表的兼容性：-Xlint=unchecked @SupperressWarning("unchecked")

## 对象包装器与自动装箱

```text
包装器类时不可变的，一旦构造了包装器，就不运行更改包装在其中的值(final)。
如果想要定义一个整型数组列表，尖括号中的类型参数不允许是基本数据类型。
当使用自动装箱方式创建一个Integer对象时，当数值在-128 ~127时，会将创建的Integer对象缓存起来，当下次再出现该数值时，直接从缓存中取出对应的Integer对象。
所有引用类型，当使用==运算符时，比较的是两个引用类型变量是否指向相同的对象，要比较引用类型变量的成员变量值是否相等，要使用equals方法。
由于包装器引用可以为null，所以自动装箱可能会抛出NullPointerException。
装箱额拆箱是编译器的工作，而不是虚拟机。
Java编译器：将Java源文件，也就是.java文件编译成字节码.class文件（二进制字节码文件），java.exe可以简单的看成是Java编译器。
Java解释器：就是把java虚拟机上运行的.class字节码解释成机器指令，让CPU识别运行。即jdk和jre中bin目录下的java.exe文件。Java解释器用来解释执行Java编译器编译后的.class文件。java.exe可以简单的看成Java的解释器。
```
```java
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        //不要使用包装器类构造器创建对象，因为包装器类缓存了对象
        Integer i = 10;
        Integer j = 10;
        System.out.println(i == j); // false
        Integer k = 10;
        Integer l = 10;
        System.out.println(k == l); // true
        Integer m = Integer.valueOf(10);
        Integer n = Integer.valueOf(10);
        System.out.println(m == n); // true
    }
}
```

## 参数个数可变的方法

```java
public class PrintStream {
    //... 表明这个方法可以接受任意个参数，args其实是一个Object[]
 public PrintStream printf(String format, Object ... args) {
        return format(format, args);
    }
}
```

```java

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        //如果一个可变参数的最后一个参数是数组，则可以把它重新定义为有可变参数的方法
        System.out.printf("Hello World! %s,%s",new Object[]{Integer.valueOf(1),"asdsd"});
        //Hello World! 1,asdsd
    }
}
```

## 抽象类

扩展一个抽象类时，可以有两种选择：
一种是在子类中保留抽象类中的部分或所有抽象方法仍未定义，这样就必须将子类也标记为抽象类；
另一种做法是定义全部方法，这样一来，子类就不再是抽象的。
抽象类不能实例化，抽象类中的抽象方法不能有方法体。但是如果省略抽象方法，就不能通过超类声明的变量名调用抽象方法了，因为编译器只允许调用在类中声明的方法。

## 枚举类

声明枚举类型可以看作是一个拥有固定个数实例的类，该类不能构造新的对象。因为在比较枚举类型时，不需要使用equals，使用==就可以比较。
可以为枚举类型添加构造器、方法和字段，构造器是私有的。所有枚举类型都市抽象类Enum的子类。

## 密封类(Java15新增)

类声明为final就不能在派生子类了。密封类声明为sealed，可以控制哪些类可以继承他，其子类必须声明为final或者sealed。指定为sealed的子类还可以继续派生final或者sealed子类。
no-sealed 允许任意派生。
一个密封类允许的子类必须是可访问的。他们不能是嵌套在另一个类中的私有类，也不能是位于另一个包中的包可见的类。他们必须与密封类在同一个包中，或者是密封类和子类位于同一个模块中。
```text
//如果不加permits，他的所有直接子类都必须在同一个文件中声明。不能访问这个文件的程序员就不能派生他的子类。
public abstract sealed class Shape permits Circle, Rectangle {}
```

## 反射

1. Class类
```text
Java运行时系统始终为所有对象维护一个运行时类型标识。这个信息会跟踪每个对象所属的类。虚拟机利用运行时类型信息选择要执行的正确方法。保存这些信息的类名为Class。
Class对象实际描述的是类型，可能是类，也可能是基本数据类型(int)。
虚拟机为每个类型管理一个唯一的Class对象，因此可以使用==运算符比较两个类对象。
```
2. 声明异常入门：throws
3. 资源：Class类提供查找资源文件的服务。
4. 利用反射分析类：
```text
reflect包中有三各类Field、Method、Constructor分别用于描述类的字段、方法和构造器。
Class类中的getFields、getMethod、getConstructors返回这个类支持的公共字段、方法和构造器数组。
Class类中的getDeclaredFields、getDeclaredMethod、getDeclaredConstructors返回这个类声明的全部字段、方法、构造器数组，包括私有成员，包成员和受保护成员。
```
5. 使用反射在运行时分析对象：可以通过Field的get、set方法获取和设置对象的值。但是受限于Java的访问机制，只能访问有权限的字段的值。不过可以调用setAccessible方法覆盖Java的访问控制。
6. 使用反射编写泛型数组代码：泛型数组无法创建，只能创建Object数组。
7. 调用任意方法和构造器：通过Class的getMethod和getConstructor方法可以获取任意方法或构造器的信息。然后调用invoke方法调用方法或构造器。

## 继承的设计技巧

```text
1. 将公共操作和字段放在超类中。
2. 不要使用受保护的字段，多子类访问protected字段，同一包中都能访问，破坏封装。
3. 使用继承实现"is-a"关系。
4. 除非所有继承方法都有意义，否则不要使用继承。
5. 覆盖方法时，不要改变预期的行为。
6. 使用多态，而不要使用类型信息。
7. 不要滥用反射：反射机制使人们可以在运行时查看字段和方法，从而编出极具通用性的代码，更适合系统编程使用不适合应用程序编程。
```

# 接口、Lambda表达式和内部类

## 接口

1. 实现接口：implements。Arrays.sort()方法只能排序实现了Comparable接口的类。
2. 接口的属性：接口变量必须引用实现这个接口的类。可以使用instanceof运算符判断对象是否实现了这个接口。接口中的方法自动为public，字段默认为public static final(java8可以在接口中定义静态字段)。
3. 接口与抽象类： 
```test
抽象类（Abstract Class）和接口（Interface）都是用来实现抽象化的工具，但它们在使用和设计上有着明显的不同。
抽象类:
    使用abstract关键字定义的，可以包含抽象方法（没有方法体的方法）和非抽象方法（有方法体的方法）。
    抽象类不能被实例化，意味着你不能创建一个抽象类的对象。
    它主要用于被其他类继承。抽象类中的抽象方法必须在子类中被实现，除非子类也是抽象类。
    此外，抽象类可以包含成员变量、构造方法、代码块（包括静态代码块）以及内部类。
接口:
    使用interface关键字定义。
    接口中的所有方法默认都是public abstract的，而成员变量默认是public static final的。
    接口不包含构造方法，因此不能被实例化。
    接口主要用于被其他类实现（implement），一个类可以实现多个接口。
```
4. 静态和私有方法：Java8中，接口可以定义静态方法，，静态方法不能被继承，只能通过接口名调用。
5. 默认方法：使用default标记，默认方法允许接口方法有方法体，不用实现类实现。
6. 默认方法冲突：
```text
两个接口定义同名同参的默认方法，实现类必须覆盖该方法，可以在实现类中选择一个默认方法（InterfaceNace.super.fucv()）
如果至少一个接口提供了默认方法，编译器就会报冲突，那么实现类必须覆盖该方法。
类优先：如果抽象类和接口定义了同名同参的抽象方法，那么抽象类方法优先。
```
7. 接口与回调：定时器调用对象的方法。这个对象必须实现ActionListener接口。
8. Comparable接口：实现Comparable接口的对象必须实现compareTo方法，compareTo方法返回一个整数，表示当前对象与参数对象之间的大小关系。
9. 对象克隆：Cloneable接口,标记接口不含有任何方法，它唯一的作用是允许在类型查询中使用（instanceof）。clone()方法是Object提供的protected方法。 深拷贝和浅拷贝。

## Lambda表达式

1. 为什么引入Lambda表达式：可传递的代码
2. Lambda表达式语法：（） -> { return  ;} //lambda表达式中可以显示的return 返回。
3. 函数式接口：对于只有一个抽象函数的接口，需要这种接口对象时，可以提供一个表达式，这种接口称为函数式接口。
4. 方法引用： 
```text
方法引用是Lambda表达式的一种简化形式，用于直接引用已有的方法或构造函数。
方法引用的语法是使用双冒号::操作符。
他指示编译器生成一个函数式接口的实例，覆盖这个接口的抽象方法来调用给定的方法。
1. 对象::实例方法名
2. 类名::静态方法名
3. 类名::实例方法名
4. 类名::new - 构造器引用
```
5. 构造器引用：ClassName::new，创建一个ClassName的实例。
6. 变量作用域：Lambda表达式的变量作用域和普通方法一样，不能引用外部方法中的局部变量。在lambda表达式中使用this，是指创建这个lambda表达式的方法的this参数。
7. 处理lambda表达式：lambda表达式的重点是延迟执行。
8. 再谈Comparator：
```text
Arrays.sort(peole, (Person a, Person b) -> a.getBirthday().compareTo(b.getBirthday()));
//调用静态comparing方法比较方法引用
Arrays.sort(people, Comparator.comparing(Person::getBirthday));
//调用实例方法引用
Arrays.sort(people, Comparator.comparing(Person::getBirthday).thenComparing(Person::getName));
Arrays.sort(people, Comparator.comparing(Person::getBirthday,(a, b)->Integer.compare(a.length(), b.length()))));
```

## 内部类

1. 使用内部类访问对象状态：
```text
内部类定义在另一个类中的类，可以对同一个包中的其他类隐藏。
内部类方法可以访问定义这些方法的作用域中的数据，包括私有的数据。
内部类的对象总有一个隐式引用，指向创建他的外部类对象(OuterClass.this)。外部类的引用在构造器中国设置，编译器会修改所有的内部类构造器，添加一个对应外部类引用的参数
```
2. 内部类的特殊语法规则：OuterClass.InnerClass
3. 内部类是否有用、必要和安全：内部类将转换为常规的类文件，用$符号分隔内部类和外部类。TalkingClock$TimePrinter.class
4. 局部内部类：声明局部类时不能有访问说明符(public\private\protected),局部类的作用域总是限定在声明这个局部类的块中。
5. 由外部方法访问变量：不仅可以访问外部类字段，还可以访问局部变量，但是这些局部变量必须是最终事实变量。
6. 匿名内部类：
```text
只创建这个类的一个对象 new SuperType(ConstructorParameters) { inner class method and data  };
构造器名称需与类名相同，由于匿名内部类没有类名，所以匿名内部类没有构造函数。虽然没有构造函数，但是可以添加初始化块。
```
7. 静态内部类：如果不需要内部类访问外部类对象，那么可以将内部类声明为静态内部类。

## 服务加载器

服务加载器：ServiceLoader.load(Class\<S> service)
在ServiceLoader.load的时候，根据传入的接口类，遍历META-INF/services目录下的以该类命名的文件中的所有类，并实例化返回。

## 代理

1. 如何使用代理：代理类会在运行时动态生成，代理类继承了被代理类，并实现相同的接口。调用处理器实现了InvocationHandler接口，代理类会调用处理器的invoke方法。
2. 创建代理类：Proxy.newProxyInstance(ClassLoader, Class[], InvocationHandler)
3. 代理类的特性：一个特定的类加载器和一组接口只能有一个代理类。代理类总是pulic final。

# 异常、断言和日志

## 处理错误
1. 异常分类：所有的异常都是由Throwable继承而来，Throwable有2个子类：Error和Exception。Error表示运行时错误，Exception表示运行时异常。
2. 声明检查型异常：throws 不需要声明Java的内部错误。一个方法必须声明所有可能抛出的检查型异常。运行时异常不需要声明。
3. 如何抛出异常：throw new Exception();
4. 创建异常类：创建异常类继承Exception。

## 捕获异常

1. 捕获异常概述：try{}catch{}finally{}
2. 捕获多个异常：try{}catch(Exception1 | Exception2 e){}catch(Exception3 e){}
3. 再次抛出异常与异常链：希望改变异常的类型时可以这么做，在catch块中thorw new Exception2(e);
4. finally: finally块中的代码会执行，即使try块中的代码抛出了异常。抛出异常如果没有被捕获而是通过throw或者throws再次抛出，将只执行finally块不执行fianlly之后的代码。
5. try-with-resources: try(Resource resource = ){work with resource}try语句结束或抛出异常时会自动调用res.close()关闭资源。
6. 分析栈轨迹元素：栈轨迹元素格式为：类名.方法名(文件名:行号)，是分析程序执行过程中国某个特定点上所有挂起的方法调用的一个列表。

## 使用异常的技巧

异常处理不能代替简单的测试、不过分的细化异常、合理利用异常的层次结构、不要压制异常

## 使用断言

1. 断言的概念：assert condition：expression 断言机制允许你在测试期间在代码中插入一些检查，而在生产代码中自动删除这些检查。
2. 启用和禁用断言：-ea/-enableAssertions -da/-disableAssertions。启用和禁止断言是类加载器的功能，不用重新编译代码。
3. 使用断言完成参数检查：断言失败是致命的，不可恢复的错误，断言失败会抛出一个AssertionError。只应该用于在测试阶段确定程序内部错误的位置。
4. 使用断言提供假设文档：

## 日志

1. 基本日志：Logger.log(Level, String)，全局日志记录器。
2. 高级日志：Logger.getLogger(String),子日志记录器会记录父日志记录器的某些属性。
3. 修改日志管理器配置：日志管理器在虚拟机启动时初始化，main函数执行前。
4. 本地化：请求一个日志记录器，可以指定一个资源包，在本地化消息中添加占位符{0}。
5. 处理器：日志记录器将记录发送到ConsoleHandler，他会将记录输出到System.err流。FileHandler将日志记录收集到文件，SocketHandler将记录发送到主机端。
6. 过滤器：根据日志级别进行过滤。
7. 格式化器：通过拓展Formatter类并重写format
8. 日志技巧：日志记录器命名为主包同名

## 调试技巧

JUnit 
日志代理：截取方法调用并记录日志
输出栈轨迹：1. 创建一个类继承Throwable，并添加一个静态方法，返回一个Throwable对象。
System.err
启动java虚拟机时，使用-verbose
-Xlint
jdk监控和管理、Jconsole

# 泛型程序设计

## 为什么要使用泛型程序设计

1. 类型参数的好处：代码可以对多种不同类型的对象重用。类型参数可以指示元素的类型。
2. 谁想成为泛型程序员：通配符类型。

## 定义简单泛型类

引入类型变量T、U，可以使用<>括起来，放在类名后面。泛型类可以有任意数量的类型参数。类型变量使用大写字母表示。
```text
泛型接口在接口被继承或被实现时确定类型参数,接口中的属性是静态的，不能使用参数类型声明,默认方法可以使用类型参数。
泛型类，在创建类的对象的时候确定类型参数的具体类型；
泛型方法，在调用方法的时候再确定类型参数的具体类型。
```

## 泛型方法

当在一个方法签名中的返回值前面声明了一个<T>时，该方法就被声明为一个泛型方法。<T>表明该方法声明了一个类型参数T，并且这个类型参数T只能在该方法中使用。

```text
（1） 只有在方法签名中声明了< T >的方法才是泛型方法，仅使用了泛型类定义的类型参数的方法并不是泛型方法。
public class Test<U> {
	// 该方法只是使用了泛型类定义的类型参数，不是泛型方法
	public void testMethod(U u){
		System.out.println(u);
	}	
	// <T> 真正声明了下面的方法是一个泛型方法
	public <T> T testMethod1(T t){
		return t;
	}
}
（2） 泛型方法中可以同时声明多个类型参数。
public class TestMethod<U> {
	public <T, S> T testMethod(T t, S s) {
		return null;
	}
}
（3）泛型方法中也可以使用泛型类中定义的泛型参数。当泛型方法的形参列表中有多个类型参数时，在不指定类型参数的情况下，方法中声明的的类型参数为泛型方法中的几种类型参数的共同父类的最小级，直到 Object。
public class TestMethod<U> {
	public <T> U testMethod(T t, U u) {
		return u;
	}
}
（4）特别注意的是：泛型类中定义的类型参数和泛型方法中定义的类型参数是相互独立的，它们一点关系都没有。
public class Test<T> {
	public void testMethod(T t) {
		System.out.println(t);
	}
	//上面代码中，Test< T > 是泛型类，testMethod() 是泛型类中的普通方法，其使用的类型参数是与泛型类中定义的类型参数。
	
	public <T> T testMethod1(T t) {
		return t;
	}
	// testMethod1() 是一个泛型方法，他使用的类型参数是与方法签名中声明的类型参数。
	// < T >表明该方法声明了一个类型参数 T，并且这个类型参数 T 只能在该方法中使用。
    //虽然泛型类中定义的类型参数标识和泛型方法中定义的类型参数标识都为< T >，但它们彼此之间是相互独立的。
    //也就是说，泛型方法始终以自己声明的类型参数为准。
	// 泛型类定义的类型参数 T 不能在静态方法中使用
    
    public static <E> E show(E one) {     
        return null;    
    } 
	//前面在泛型类的定义中提到，在静态成员中不能使用泛型类定义的类型参数，但我们可以将静态成员方法定义为一个泛型方法。
	//泛型类定义的类型参数 T 不能在静态方法中使用
	//但可以将静态方法声明为泛型方法，方法中便可以使用其声明的类型参数了
}

```
## 类型变量的限定

限定类型变量只实现了某个接口，<T extends Interface1 & Interface2>

## 泛型代码和虚拟机

1. 类型擦除：Java 编译器是先检查代码中传入 < T > 的数据类型，并记录下来，然后再对代码进行编译，编译的同时进行类型擦除；如果需要对被擦除了泛型信息的对象进行操作，编译器会自动将对象进行类型转换。编译器会擦除类型变量，并替换为其限定类型或Object，如果限定多个类，则使用在前面的限定类型。
2. 转换泛型表达式：编写一个泛型方法调用时，如果擦除了返回类型，编译器会插入强制类型转换。
3. 转换泛型方法：
```text
继承或实现限定类型时，。父类是泛型类，且在该类中存在泛型方法，子类继承父类并实现泛型方法。
如果在子类实现中不包含父类经过类型擦除后生成的原始类型方法，则编译器会自动将该原始类型方法添加到子类中。这个被添加的子类就桥接方法。
桥方法：由java编译器生成，实现泛型接口的方法。覆盖超类方法并升级返回类型。
所有类型在编译后都会被擦除为Object。为了确保泛型方法在运行时能正确调用，编译器会生成一个桥方法来处理类型转换和方法调用。
```
4. 调用遗留代码：@SuppressWarnings("unchecked")取消警告。

## 限制与局限性

1. 不能使用基本类型实例化类型参数：类型擦除时无限定类型的参数会被擦除为Object，Object不能存储基本数据类型。
2. 运行时类型查询只适用于原始类型： 
```text
虚拟机中的对象总是有一个特定的非泛型类型，所有的类型查询只生成原始类型。
instanceof和getclass总是返回原始类型。
Pair<String> p = ...
Pair<Empolyee> e= ...
if(p.getClass()==e.getCLass()) {} //they are equal

public void test() {
	Generic<String> generic = new Generic<>();// 传入 String 类型
	// <> 中什么都不传入，等价于 Generic<Object> generic = new Generic<>();
	Generic generic = new Generic();
}

```
3. 不能创建参数化类型的数组：
```text
java中子类数组可以赋值给父类数组变量。参数化类型的数组擦除后转换成限定类数组或Object[]。
var table = new Pair<String>()[10];//编译错误,假设可以，Pair编译时擦除为Object[]
Object[] objects = table; //擦除时，对象数组被擦除为Object[]，objects类型不会报错，但是数组会记住他的元素类型Pair，运行时会检查数组存储类型。
objects[0] = "Hello"; //ArrayStoreException,数组只能存储创建时元素类型。
//不过对于泛型类型，擦除会使这种机制无效
objects[0] = new Pair<String>(); //不会报错,尽管能通过数组存储检查，但是仍会导致类型错误。
```
4. Varargs警告：
```text
向参数个数可变的方法传递一个泛型类型的实例：数组元素的数据类型在编译和运行时都是确定的，而泛型的数据类型只有在运行时才能确定。
因此当把一个泛型存储到数组中时，编译器在编译阶段无法检查数据类型是否匹配，所以会给出警告。
public static <T> void addAll(Collection<T> coll,T... elements) { //实际上elements的类型是Object[]
    for(T e: elements) {
        coll.add(e);
    }
}
Collection<Pair<String>> c = new ArrayList<Pair<String>>()[];//会被警告，使用@SuppressWarnings("unchecked")取消警告。
Pair<String> p = new Pair<String>();
addAll(c, p);
//或者使用@SafarVarargs直接注解addAll方法取消警告，对于任何只需要读取参数数组元素的方法都可使用，其只能用于声明static、final或private的构造器和方法。也编译时使用-Xlint:unchecked。
```
5. 不能实例化类型变量：不能在new T(...)的表达式中使用类型变量。java8让调用者传构造器表达式，用方法接受一个函数式接口(Supplier<T>)。
```text
//让调用者提供构造器表达式
Pair<String> p1 = Pair.newInstance(() -> new String());//函数式接口
Pair<String> p2 = Pair.newInstance(String::new);
class Pair<T> {
    public static <T> T newInstance(Supplier<T> constructor) { //Supplier<T> 函数式接口
        return new Pair<>(constructor.get(), constructor.get()); //get()方法不接受任何参数，但返回一个泛型类型T的实实例。
    }
}
//利用反射
public static <T> T newInstance(Class<T> cls) {
    try{
        return new Pair<>(cls.getConstructor().newInstance(), cls.getConstructor().newInstance());//getConstructor()获取指定参数类型的公共构造函数
    }catch(Exception e){
        return null;
    }
}
```
6. 不能构造泛型数组：
```text
数组本身也带有类型，用来监控虚拟机中的数组存储，这个类型会被擦除。
类型擦除会导致总是构造限定类型数组，在强制转换，在强制转换时会抛出ClassCastException。
若提供数组构造器表达式
public static <T extends Comparable> T[] newArray(IntFunction<T[]> constr, T... a){
    T[] result = constr.apply(length);
}
public static <T extends Comparable> T[] newArray(Class<T> clazz, int length){
    var result = (T[]) Array.newInstance(clazz, length);
}
```
7. 泛型类的静态上下文中类型变量无效：
```text
泛型类中的静态方法和静态变量不可以使用泛型类所声明的类型参数。
泛型类中的类型参数是在创建泛型类实例时（new ArraysList<String>();）确定的，而静态变量和静态方法在类加载是初始化，直接使用类名调用。
在泛型类的类型参数还没确定时，静态成员有可能会被调用。
静态泛型方法中可以使用自身的方法签名中新定义的类型参数（即泛型方法，后面会说到），而不能使用泛型类中定义的类型参数。
public class Test2<T> {   
	// 泛型类定义的类型参数 T 不能在静态方法中使用  
    public static <E> E show(E one){ // 这是正确的，因为 E 是在静态方法签名中新定义的类型参数    
        return null;    
    }    
}  

```
8. 不能抛出或捕获泛型类的实例：既不能抛出也不能捕获泛型类的对象。泛型类不能拓展Throwable。catch中不能使用类型变量。但是可以在异常规范中使用类型变量。
```text
public static <T extends Throwable> void doWork(T t) throws T{
    try{
        do work
    }
    catch(T t){
        t.initCase(reason);
        throw t;
    }
}
```
9. 可以取消对检查型异常的检查：在java中必须为所有检查型异常提供一个处理器，不过可以利用泛型取消这个机制。
```text
@SuppressWarnings("unchecked")
static <T extends Throwable> void throwAs(Throwable t) throws T{//如果该方法在一个接口中，如果调用接口的throwsAs(e)方法，编译器会认为e是一个非检查型异常。
    throw (T)t;
}
```
10. 注意擦除后的冲突：倘若两个接口类型是同一接口的不同参数化,一个类或类型变量就不能同时作为这两个接口类型的子类，因为擦除后的类型相同，合成桥方法会产生冲突。

## 泛型类型的继承规则

泛型中无论R和T有什么关系，Pair<R>和Pair<T>都没有任何关系。总是可以将参数化类型转换为一个原始类型。泛型类可以实现和拓展其他泛型类
```text
var managerBuddies=new Pair<String>(bob,sue);
Pair buddies=managerBuddies; // OK  参数化类型转换为一个原始类型
buddies.setFirst(fred); // 警告：转换成原始类型后，无法保证类型安全
```

## 通配符类型

1. 通配符概念：通配符类型，如?，表示任意类型。? extends 表示类型参数的上限，限制泛型类型参数为某个类的子类。声明泛型类型时不指定具体的类型参数。不能向通配符的泛型对象中添加任何具体类型的元素，但可以读取泛型对象中的元素。
2. 通配符的超类型限定：? super 表示类型参数的下限，限制泛型类型参数为某个类的超类，允许添加任意类型参数的元素。
3. 无限定通配符：？，判断null比较方便。
4. 通配符捕获：不能编写使用?作为一种类型的代码。可以使用泛型T t=p.getFirst()作为类型。

## 反射和泛型

1. 泛型Class类
2. 使用Class<T>参数进行类型匹配
3. 虚拟机中的泛型类型信息
4. 类型字面量

# 集合

## Java集合框架

1. 集合接口与实现分离：
```text
队列接口：可以在队尾添加元素，也可以在队首删除元素，也可以在队列中查找元素个数，先进先出。
队列通常由两种实现：循环数组和链表。
```
2. Collection接口：
3. 迭代器：Iterator 接口,
```test
for each 循环可以处理任何实现Iterator接口的对象。
调用forEachRemaining方法并提供lambda表达式,iterator.forEachRemaining(element->System.out.println(element));
next()和remove()方法存在依赖关系，it.next();it.remove();
```
4. 泛型实用方法：Conllection接口，Set不允许添加重复的元素

## 集合框架中的接口

- Iterable
  - Collection
    -  List
    -  Set
      -  SortedSet
        - NavigableSet：
    -  Queue：队列
      - Deque：双向队列
- Map 
  - SortedMap
    - NavigableMap
- Iterator
  - ListIterator：支持双向遍历
- RandomAccess：不包含任何方法，表示集合中的元素可以以随机访问的方式访问。
    

## 具体集合

- AbstractCollection
    - AbstractList
      - ArrayList：动态增长或缩减的列表
      - AbstractSequentialList
        - LinkedList：链表，在任意位置添加或删除元素。
    - AbstractSet
      - HashSet：无序集合，不允许重复元素
        - LinkedHashSet：插入顺序的集合
      - TreeSet：有序集合，不允许重复元素
      - EnumSet：枚举集合
    - AbstractQueue
      - PrioityQueue：允许高效删除最小元素的队列
      - ArrayDueue: 循环数组的双端队列
- AstractMap
  - HashMap：键值对存储
    - LinkedHashMap：可以记住键值添加顺序
  - TreeMap：键有序的映射
  - EnumMap：键属于枚举的映射
  - WeakHashMap：映射中的值会被垃圾回收
  - IdentityHashMap：键和值都使用==比较

1. 链表：LinkedList，java程序设计语言中，所有链表实际上都有双向链接。
2. 数组列表：ArrayList
3. 散列集：HashSet，为每个对象计算一个散列码。散列集迭代器将一次访问所有的桶。
4. 树集：TreeSet,有序集合，红黑树实现（自平衡的二叉查找树）
5. 队列与双端队列：Dueue，队列接口，包含add()、remove()、element()、peek()方法。
6. 优先队列：PriorityQueue，任意顺序插入，会按照有序顺序获取。堆，堆的实现是二叉树，添加和删除操作会移动最小的元素到根。

## 映射

1. 基本映射操作：键必须是唯一的。从映射中删除一个键，值也随之删除。
2. 更新映射条目：映射的键值对可以更新。
3. 映射试图：实现了Collectio接口或某个子接口的对象。映射视图的修改会反映到映射中。
4. 弱散列映射：WeakHashMap,将键的唯一引用来自散列表条目时，该键值对会被垃圾回收。
5. 链接散列集与映射：LinkedHashMap，会记录插入元素的顺序，链接散列表可以使用访问顺序或插入顺序，来迭代处理映射条目。每次调用put或get,收到影响的元素将移动到链表末尾。
6. 枚举集与映射：EnumSet,其元素属于枚举类型，没有构造器，使用静态工厂方法创建。
7. 标识散列映射：IdentityHashMap,键和值都使用==比较，不同键对象即使内容相同，也视为不同的对象。

## 副本与试图

1. 小集合：
```text
java9中引入了一些静态方法，可以生成简单直接的拥有给定元素的集或列表。
of()生成一个不可修改的集合。如果需要修改集合，请将该集合传到构造器中。
List.of("1","asd","3")
Set.of(1,2,3)
Map.of("asdqwe",1,"eqwe":1,"weqwe":2,"qweqwedf":2)
Map.ofEntries(Map.entry("asdqwe",1),Map.entry("eqwe",1),Map.entry("weqwe",2),Map.entry("qweqwedf",2))
```
2. 不可修改的副本和视图：如果多个线程访问同一个集合，必须确保集合不会被意外的破坏，对于没有实现线程安全的集合类，可以用集合的副本或视图。
```text
不可修改的副本：例如copyof()都会创建一个不可修改的副本，如果修改原集合，副本不受影响。如果这个集合刚好本身就是不可修改的，copyof()则直接返回原集合。
不可修改的视图：Collections类有一些方法(unmodifiableXXX())可以生成集合的不可修改的视图，这些试图对现有集合增加了运行时检查，如果检测到试图修改不可修改的集合，会抛出UnsupportedOperationException。
             如果集合改变，视图会自动更新。视图集合的equals()方法不会调用底层集合的equals()方法，仅仅检查两个对象是否是在同一地址的完全相同的对象。
```
3. 子范围：创建子范围集合，子范围集合是原集合的子集。subList(a,b),第一个索引包含在内，不包含第二个索引。可以对子范围应用任何操作，并且会自动反映到整个列表。
4. 检查型视图：对泛型类型可能出现的问题提供调试支持。Collections.checkedList();
5. 同步视图：Collections的synchronizedMap()可以将任何一个映射转换成有同步访问方法的map。
6. 关于可选操作的说明：

## 算法

1. 为什么使用泛型算法：泛型算法可以处理任意类型的对象，而不仅仅是基本类型。
2. 排序和混排：sort(),shuffle()
3. 二分查找：Collections.binarySearch(list,element), 返回元素在列表中的索引，如果列表中不存在该元素，返回负数。集合必须有序。
4. 简单算法：Collections.min(),Collections.max()......
5. 批操作: 成批复制或删除元素。
6. 集合与数组的转换：List.of(a,b,c);toArray(); Arrays.asList(a,b,c); toArray()返回一个Object[]数组，不能改变他的类型，但是可以向toArray(String[]::new)传递一个数组构造器，创建一个指定类型的数组。
7. 编写自己的算法：创建一个类，实现Comparator接口，并实现compare()方法。

## 遗留的集合：
1. HashTable类
2. 枚举:Enumeration()
3. 属性映射：键值都是字符串，可以保存到属性文件中。Properties类可以使用store方法将属性保存到文件中。
4. 栈：Stack类，继承自Vector类，Vector类是线程安全的，Stack类不是。Stack类提供了push()和pop()方法，用于将元素添加到栈顶，并返回栈顶的元素。
5. 位集：BitSet类，用于存储位。提供了一个用于读取，设置或重置各个位的接口。