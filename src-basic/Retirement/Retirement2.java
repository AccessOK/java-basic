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
