package EmployeeTest;

import java.time.LocalDate;

public class EmployeeTest {
    private String name;
    private double salary;
    private LocalDate hireDay;
    //构造函数
    public EmployeeTest(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public LocalDate getHireDay() {
        return hireDay;
    }
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
    public static void main(String[] args) {
        EmployeeTest employee = new EmployeeTest("Bob", 50000, 1989, 10, 1);
        System.out.println(employee.getName() + " earned " + employee.getSalary());
    }
}
