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