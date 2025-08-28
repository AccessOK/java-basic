package genericReflection;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

public class GenericReflectionTest {
    public static void main(String[] args){
        String name;
        if(args.length>0) name=args[0];
        else {
            try(Scanner in=new Scanner(System.in)){
                System.out.println("Enter class name (e.g., java.util.Collections):");
                name=in.next();
            }
        }
        try{
            //根据类名查找类型
            Class<?> cl=Class.forName(name);
            printClass(cl); //输出父类和接口
            for(Method m:cl.getDeclaredMethods()){
                printMethod(m);
            }
        } catch (ClassNotFoundException e) {
            e.getException();
        }
    }
    public static void printClass(Class<?> cl){
        System.out.print(cl); //class pair.Pair1
        printTypes(cl.getTypeParameters(),"<",",",">",true); //<
        Type sc=cl.getGenericSuperclass(); //获取父类
        if(sc!=null){
            System.out.print(" extends ");
            printType(sc,false);
        }
        printTypes(cl.getGenericInterfaces()," implements ",",",",",false); //获取接口
        System.out.println();
    }
    public static void printMethod(Method m){
        String name= m.getName();
        System.out.print(Modifier.toString(m.getModifiers())); //访问权限修饰符
        System.out.print(" ");
        printType(m.getGenericReturnType(),false); //返回值类型
        System.out.print(" ") ;
        System.out.print(name); //方法名
        System.out.print("(");
        printTypes(m.getGenericParameterTypes(),"",",","",false);
        System.out.println(")");
    }
    public static void printTypes(Type[] types,String pre,String sep,String suf,boolean isDefinition){
        if(pre.equals(" extends ")&& Arrays.equals(types,new Type[]{Object.class}))return;
        if(types.length>0) System.out.print(pre);
        for(int i=0;i<types.length;i++){
            if(i>0)System.out.print(sep);
            printType(types[i],isDefinition); //输出参数类型
        }
        if(types.length>0)System.out.print(suf);
    }
    public static void printType(Type type,boolean isDefinition){
        if(type instanceof Class t){ //运行时类的类型信息
            System.out.print(t.getName());
        }else if(type instanceof TypeVariable t){//泛型类型的占位符
            System.out.print(t.getName());
            if(isDefinition)printTypes(t.getBounds()," extends ","&","",false);
        } else if (type instanceof WildcardType t) {//通配符类型
            System.out.print("?");
            printTypes(t.getUpperBounds()," extends "," & ","",false);
            printTypes(t.getUpperBounds()," super "," & ","",false);
        } else if (type instanceof ParameterizedType t) {//参数化类型，获取泛型的实际类型参数
            Type owner =t.getOwnerType();
            if (owner!=null){
                printType(owner,false);
                System.out.print(".");
            }
            printType(t.getRawType(),false);
            printTypes(t.getActualTypeArguments(),"<",",",">",false);
        } else if (type instanceof GenericArrayType t) {// 泛型数组
            System.out.print("");
            printType(t.getGenericComponentType(),isDefinition);
            System.out.print("[]");
        }
    }
}
