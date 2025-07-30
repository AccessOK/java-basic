package linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListTest {
    public static void main(String[] args){
        LinkedList<String> a=new LinkedList<>();
        a.add("aAmy");
        a.add("aBob");
        a.add("aCindy");
        a.add("aDiana");
        LinkedList<String> b=new LinkedList<>();
        b.add("bBob");
        b.add("bCindy");
        b.add("bDiana");
        b.add("basdw");
        b.add("basdqwe");
        ListIterator<String> aIter=a.listIterator(); //listIterator()方法更多
        Iterator<String> bIter=b.iterator();
        while (bIter.hasNext()){
            if(aIter.hasNext())aIter.next();
            aIter.add(bIter.next());
        }
        System.out.println(a);//[aAmy, bBob, aBob, bCindy, aCindy, bDiana, aDiana]
        bIter=b.iterator();
        while (bIter.hasNext()){
            bIter.next();
            if(bIter.hasNext())
            {
                bIter.next();
                bIter.remove();
            }
        }
        System.out.println(b);
        a.removeAll(b);//删除a中b的元素
        System.out.println(a);
    }
}
