package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyTest {
    public static void main(String[] args) {
        Object[] objs = new Object[1000];
        for (int i = 0; i < objs.length; i++) {
            int value=i+1;
            //创建代理
            objs[i] = Proxy.newProxyInstance(
                    //类加载器
                    ClassLoader.getSystemClassLoader(),
                    //代理的接口
                    new Class[]{Comparable.class},
                    //处理器：调用代理对象的方法都会调用处理器的invoke方法，并提供Method 对象和原调用参数
                    new TraceHandler(value));
        }
        Integer key=(int)(Math.random()* objs.length)+1;
        //调用原始方法
        int result= Arrays.binarySearch(objs, key);
        if(result>=0)System.out.println("找到"+key);
    }

}
//实现一个包装器类，用于存储一个包装的对象
class TraceHandler implements InvocationHandler {
    private Object target;
    public TraceHandler(Object t) {
        target = t;
    }
    // 实现InvocationHandler接口的invoke方法
    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("." + m.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1)
                    System.out.print(", ");
            }
        }
        System.out.println(")");
        //Array
        return m.invoke(target, args);
    }

}