package arrayList;

import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args)
    {
        ArrayList<String> staff = new ArrayList<>();
        staff.add("Carl Cracker");
        staff.add("Harry Hacker");
        staff.add("Tony Tester");
        for (String name : staff){
            System.out.println(name);
        }
    }
}
