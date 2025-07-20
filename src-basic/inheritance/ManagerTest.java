package inheritance;

public class ManagerTest {
    public static void main(String[] args) {
        var boss = new Manager("Carl",8000,1987,1,1);
        boss.setBonus(5000);
        System.out.println(boss.getSalary());
        var staff = new Employee[3];
        staff[0] = boss;
        staff[1] = new Employee("Harry",5000,1989,3,3);
        staff[2] = new Employee("Tony",4000,1990,12,25);
        for (Employee e : staff)
        {
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
            e.raiseSalary(5);
        }
        for (Employee e : staff)
        {
            if(e instanceof Manager){
                Manager m = (Manager) e;
                m.setBonus(2000);
                System.out.println("name=" + m.getName() + ",salary=" + m.getSalary() );
            }
            //Java16进阶,如果e时Manager类型，则变量m会自动转为Manager类型，可以跳过强制类型转换。
            if(e instanceof Manager m){
                m.setBonus(2001);
                System.out.println("name=" + m.getName() + ",salary=" + m.getSalary() );
            }
        }
    }
}
