package outOfMemory;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryTest {
    public static void main(String[] args){
        //不断向堆内存中添加对象，会报错：Java heap space
        //-XX:+HeapDumpOnOutOfMemoryError : 当内存溢出时，生成一个dump文件
        //-Xms20m: 设置初始堆内存大小
        //-Xmx20m: 设置最大堆内存大小
        List<Demo> list=new ArrayList<>();
        while(true){
            list.add(new Demo());
        }
    }
}
class Demo{

}