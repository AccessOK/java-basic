package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionTest {
    public static void main(String[] args) throws Exception
    {
        String name;
        if (args.length > 0) name = args[0];
        else {
            var in =new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }
        Class<?> cl = Class.forName(name);
        String modifiers = Modifier.toString(cl.getModifiers()); //获取类修饰符 public final
        if(!modifiers.isEmpty())System.out.print(modifiers+" ");
        if(cl.isSealed())System.out.print("sealed "); //判断类是否是密封的
        if(cl.isEnum())System.out.print("enum "+name); //判断类是否是枚举
        else if(cl.isRecord())System.out.print("record "+ name); //判断类是否是记录
        else if(cl.isInterface())System.out.print("interface "+name); //判断类是否是接口
        else System.out.print("class "+name); //判断类是否是类
        Class<?> supercl = cl.getSuperclass(); //获取父类
        if(supercl != null && supercl != Object.class){
            System.out.print(" extends "+supercl.getName());
        }
        //输出实现关系接口
        printInterfaces(cl);
        //如果是密封类，判断其允许的类
        printPermittedSubclasses( cl);
        //先输出构造方法
        System.out.print("\n{\n");
        printConstructors(cl);
        System.out.println();
        //输出方法
        printMethods(cl);
        System.out.println();
        //输出属性
        printFields(cl);
        System.out.println("}");
    }

    public static void printConstructors(Class<?> cl)
    {
        Constructor<?>[] constructors = cl.getConstructors();
        for(Constructor<?> c : constructors)
        {
            String name= c.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (!modifiers.isEmpty()) System.out.print(modifiers+" ");
            System.out.print(name+"(");
            Class<?>[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(")");
        }
    }
    public static void printMethods(Class<?> cl){
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods){
            Class<?> retType = m.getReturnType();
            String name = m.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(m.getModifiers());
            if (!modifiers.isEmpty()) System.out.print(modifiers+" ");
            System.out.print(retType.getName()+" "+name+"(");
            Class<?>[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(")");
        }
    }
    public static void printFields(Class<?> cl){
        Field[] fields = cl.getFields();
        for(Field f : fields)
        {
            Class<?> type = f.getType();
            String name = f.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (!modifiers.isEmpty()) System.out.print(modifiers+" ");
            System.out.println(type.getName()+" "+name+";");
        }
    }

    public static void printPermittedSubclasses(Class cl){
        if(cl.isSealed()){
            Class<?>[] permittedSubclasses = cl.getPermittedSubclasses();
            for(int i=0;i<permittedSubclasses.length;i++){
                if(i==0){
                    System.out.print("permitted ");
                }else {
                    System.out.print(", ");
                }
                System.out.print(permittedSubclasses[i].getName());
            }

        }
    }

    public static void printInterfaces(Class<?> cl){
        Class<?>[] interfaces = cl.getInterfaces();
        for(int i=0;i<interfaces.length;i++){
            if(i==0){
                System.out.print(cl.isInterface() ? " extends ":" implements ");
            }else {
                System.out.print(", ");
            }
            System.out.print(interfaces[i].getName());
        }
    }
}
