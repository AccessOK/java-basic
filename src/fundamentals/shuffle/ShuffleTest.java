package shuffle;

import java.util.*;

public class ShuffleTest {
    public static void main(String[] args) {
        List<Integer> numbers=new ArrayList<>();
        for (int i = 1; i <= 49; i++)
            numbers.add(i);
        Collections.shuffle(numbers);
        System.out.println(numbers);
        List<Integer> winningCombination=numbers.subList(0, 6);
        Collections.sort(winningCombination);
        System.out.println("Winning Combination: " + winningCombination);
        var a= Arrays.asList(1,2,3);
//        a.add(4); // 不可修改
        a.set(0, 4);
        System.out.println(a);
    }
}
