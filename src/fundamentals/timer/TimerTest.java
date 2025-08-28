package timer;

import javax.swing.*;

public class TimerTest {
    public static void main(String[] args) {
        var clock = new TimerPrinter();
        Timer t = new Timer(1000, clock);
        t.start();
        System.out.println("Press Enter to exit");
        new java.util.Scanner(System.in).nextLine();
        //通常用于正常退出程序，表示程序成功执行并且没有错误
        System.exit(0);
    }
}
