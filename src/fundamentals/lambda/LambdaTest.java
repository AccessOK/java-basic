package lambda;

import java.util.Arrays;

public class LambdaTest {
    public static void main(String[] args){
        String[] names= {"Tom","Jerry","Mike","Mary","Jim"};
        System.out.println("原始顺序："+ Arrays.toString(names));
        Arrays.sort(names,(String s1,String s2)->s1.length()-s2.length());
        System.out.println("按长度排序："+Arrays.toString(names));

    }
}
