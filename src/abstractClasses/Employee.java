package abstractClasses;

import java.time.LocalDate;
import java.util.Locale;

public class Employee extends  Person{
    private  double salary;
    private LocalDate hireDay;
    public Employee(String name,double salary,int year,int month,int day)
    {
        super(name);
        this.salary=salary;
        hireDay= LocalDate.of(year,month,day);
    }
    public  double getSalary()
    {
        return salary;
    }
    public LocalDate getHireDay()
    {
        return hireDay;
    }
    public String getDescription()
    {
        return String.format("an employee with a salary of $%.2f",salary);
    }
    public void rasiseSalary(double percent)
    {
        double raise=salary*percent/100;
        salary+=raise;
    }
}
