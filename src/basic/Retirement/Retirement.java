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
