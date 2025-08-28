package genericReflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//泛型类
class TypeLiteral <T>{
    private Type type;
    //构造方法
    public TypeLiteral(){
        Type parentType = getClass().getGenericSuperclass();//获取当前类泛型参数
        if(parentType instanceof ParameterizedType paramType){
            type=paramType.getActualTypeArguments()[0];//泛型类型的实际类型参数
        }else{
            throw new UnsupportedOperationException("Construct as new TypeLiteral<...>(){}");
        }
    }
    private TypeLiteral(Type type){
        this.type=type;
    }
    public static TypeLiteral<?> of (Type type){
        return new TypeLiteral<Object>(type); //静态方法of()创建Object对象
    }
    public String toString(){
        if(type instanceof Class cl)return cl.getName();
        else return type.toString();
    }
    public boolean equals(Object o){
        return o instanceof TypeLiteral otherLitetal && type.equals(otherLitetal.type); //如果类型相同，判断两个对象是否相等
    }
    public int hashCode(){
        return type.hashCode();
    }
}
class Formatter{
    private Map<TypeLiteral<?>,Function<?,String>> rules=new HashMap<>(); //定义map
    public <T> void forType(TypeLiteral<T> type, Function<T,String> formatterForType){
        rules.put(type,formatterForType);
    }
    public String formatFields(Object obj) throws IllegalArgumentException,IllegalAccessException{
        var result=new StringBuilder();
        for(Field f:obj.getClass().getDeclaredFields()){
           result.append(f.getName());
           result.append("=");
           f.setAccessible( true);
           Function<?,String> formatterForType = rules.get(TypeLiteral.of(f.getGenericType()));
           if(formatterForType!=null){
               @SuppressWarnings("unchecked")
               Function<Object,String> objectFormatter = (Function<Object,String>) formatterForType;
               result.append(objectFormatter.apply(f.get(obj)));
           }else{
               result.append(f.get(obj)).toString();
           }
           result.append("\n");
        }
        return result.toString();
    }
}
public  class TypeLiterals{
    public static class Sample{
        ArrayList<Integer> nums;
        ArrayList<Character> chars;
        ArrayList<String> strings;
        public Sample(){
            nums = new ArrayList<Integer>();
            nums.add(42);
            nums.add(1729);
            chars = new ArrayList<Character>();
            chars.add('H');
            chars.add('i');
            strings = new ArrayList<String>();
            strings.add("Hello");
            strings.add("World");
        }
    }
    public static <T> String join(String separator, ArrayList<T> elements){
        StringBuilder result = new StringBuilder();
        for(T element: elements){
            if(result.length() > 0){
                result.append(separator);
            }
            result.append(element.toString());
        }
        return result.toString();
    }
    public static void main(String[] args) throws Exception{
        Formatter formatter=new Formatter();
        formatter.forType(new TypeLiteral<ArrayList<Integer>>(){},lst->join(" ",lst));
        formatter.forType(new TypeLiteral<ArrayList<Character>>(){},lst->"\""+join("",lst)+"\"");
        System.out.println(formatter.formatFields(new Sample()));
    }
}