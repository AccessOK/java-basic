package outOfMemory;

import java.util.List;

public class JconsoleTest {
    public byte[] big = new byte[1024 * 1024];
    public static void main(String[] args) {
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("start....");
        fill(10000);
    }
    public static void fill(int count){
        List<JconsoleTest> list = new java.util.ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println(i);
            try {
                Thread.sleep(50);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            list.add(new JconsoleTest());
        }
    }
}
