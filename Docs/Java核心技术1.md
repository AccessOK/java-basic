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
2. 构造器：标准、自定义和简洁。

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
        Integer i = new Integer(10);
        Integer j = new Integer(10);
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

