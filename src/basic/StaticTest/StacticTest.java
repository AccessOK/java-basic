package StaticTest;

public class StacticTest {
    public static void main(String[] args) {
        var a=new Empoloyee[3];
        a[0]=new Empoloyee("Harry",50000);
        a[1]=new Empoloyee("Carl",60000);
        a[2]=new Empoloyee("Tony",70000);
        for(Empoloyee e:a){
            System.out.println("name="+e.getName()+",id="+e.getId()+",salary="+e.getSalary());
        }
        int n=Empoloyee.advanceId();
        System.out.println("Next id="+n);
    }
}

class Empoloyee {
    private static int nextId = 1;
    private int id;
    private String name;
    private double salary;
    public Empoloyee(String n,double s) {
        name=n;
        salary=s;
        id=advanceId();
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public int getId() {
        return id;
    }
    public static int advanceId() {
        return nextId++;
    }
    public static void main(String[] args) {
        Empoloyee e1=new Empoloyee("Harry",50000);
        System.out.println(e1.getName()+"'s id="+e1.getId());
    }

}