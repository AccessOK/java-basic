package treeSet;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<Item> tree = new TreeSet<>();
        tree.add(new Item("Dog", 1));
        tree.add(new Item("Cat", 2));
        tree.add(new Item("Bird", 3));
        tree.add(new Item("Lion", 4));
        System.out.println( tree);
        TreeSet<Item> sortDescription=new TreeSet<>(Comparator.comparing(Item::getDescription));
        sortDescription.addAll(tree);
        System.out.println(sortDescription);
    }
}
