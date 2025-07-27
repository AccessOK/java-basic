package pair;

import inheritance.Manager;
import inheritance.Employee;

public class PairTest3 {
    public static void main(String[] args){
        //实例化ceo和cfo数据
        var ceo=new Manager("Gus Greedy", 8000, 1984, 11, 1);
        var cfo=new Manager("Susan Lucky", 6000, 1989, 3, 15);
        //实例化泛型类型
        var buddies=new Pair<Manager>(ceo, cfo);
        printBuddies(buddies);
        ceo.setBonus(100000);
        cfo.setBonus(50000);
        Manager[] managers= {ceo,cfo};
        var result=new Pair<Employee>();
        minmaxBonus(managers,result);
        System.out.println("first:"+result.getFirst().getName()+",second:"+result.getSecond().getName());
    }
    public static void printBuddies(Pair<? extends Employee> pair){
        Employee first=pair.getFirst();
        Employee second=pair.getSecond();
        System.out.println(first.getName()+" and "+second.getName()+" are buddies.");
    }
    public static void minmaxBonus(Manager[] a,Pair<? super Manager> result){
        if(a.length==0)return;
        Manager min=a[0];
        Manager max=a[0];
        for(int i=1;i<a.length;i++){
            if(min.getSalary()>a[i].getSalary())min=a[i];
            if(max.getSalary()<a[i].getSalary())max=a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }
    public static void maxminBonus(Manager[] a,Pair<? super Manager> result){
        minmaxBonus(a,result);
        PairAlg.hasNulls( result);
    }
}
class PairAlg{
    public static boolean hasNulls(Pair<?> p){
        return p.getFirst()==null||p.getSecond()==null;
    }
    public static void swap(Pair<?> p){
        swapHelper(p);
    }
    private static <T> void swapHelper(Pair<T> p){
        T t=p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}