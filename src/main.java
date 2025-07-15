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