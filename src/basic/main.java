
import interfaces.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Employee employee1=new Employee("John", 30, 5000);
        Employee employee2=new Employee("Mike", 25, 4000);
        Employee employee3=new Employee("Jane", 35, 6000);
        Employee employee4=new Employee("Jim", 28, 5500);
        Employee employee5=new Employee("Mary", 32, 4500);
        Employee[] employees={employee1, employee2, employee3, employee4, employee5};
        System.out.println(Arrays.toString(employees));
        //Arrays.sort(employees,构造器);
        //Comparator.comparing(Employee::getSalary) 通过静态方法创建一个构造器：(c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
        Arrays.sort(employees, Comparator.comparing(Employee::getSalary).thenComparing(Employee::getAge));
        System.out.println(Arrays.toString(employees));
        //创建一个构造器：(c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
        Arrays.sort( employees,Comparator.comparing(Employee::getName,(s, t)->Integer.compare(s.length(), t.length())));
        System.out.println(Arrays.toString(employees));
    }
}