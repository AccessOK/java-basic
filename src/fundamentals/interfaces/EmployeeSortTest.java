package interfaces;

import java.lang.reflect.Array;
import java.util.Arrays;

public class EmployeeSortTest {
    public static void main(String[] args) {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Harry Hacker", 23, 12000);
        staff[1] = new Employee("Carl Cracker", 21, 6000);
        staff[2] = new Employee("Tony Tester", 32, 4000);
        Arrays.sort( staff);
        for (Employee e : staff){
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary() + ",age=" + e.getAge());
        }
    }
}
