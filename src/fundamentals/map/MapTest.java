package map;

import interfaces.Employee;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Employee> map = new HashMap<>();
        map.put("1", new Employee("张三", 30, 5000));
        map.put("2", new Employee("李四", 25, 4000));
        map.put("3", new Employee("王五", 28, 4500));
        map.put("4", new Employee("赵六", 32, 6000));
        System.out.println(map);
        map.remove("1");
        System.out.println(map);
        map.put("5", new Employee("张三", 30, 5000));
        map.forEach((k,v)->System.out.println(k+" "+v));
    }
}
