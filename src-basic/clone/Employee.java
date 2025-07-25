package clone;

import java.util.Date;

public class Employee implements Cloneable{
    private String name;
    private double salary;
    private Date hireDay;
    public Employee(String name, double salary)
    {
        this.name = name;
        this.salary = salary;
        hireDay = new Date();
    }
    public Employee clone() throws CloneNotSupportedException
    {
        Employee cloned = (Employee)super.clone();
        cloned.hireDay = (Date)hireDay.clone();
        return cloned;
    }
    public void setHireDay(int year, int month, int day)
    {
        Date newHireDay = new Date(year, month - 1, day);
        hireDay.setTime(newHireDay.getTime());
    }
    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
    public String toString()
    {
        return "Employee[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }
}
