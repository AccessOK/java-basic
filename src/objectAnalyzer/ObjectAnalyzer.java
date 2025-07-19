package objectAnalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();//对象数组
    //toString()方法
    public String toString(Object obj){
        if(obj == null) return "null";
        //检测对象是否已经访问过
        if(visited.contains(obj)) return "...";
        //没有被访问过的对象添加到数组中
        visited.add(obj);
        //获取对象类
        Class<?> cl = obj.getClass();
        //如果本省就是String类，返回字符串本身
        if(cl == String.class) return (String)obj;
        //如果对象是数组，则返回数组的字符串表示
        if(cl.isArray()) {
            //cl.getComponentType()获取数组类型的元素类型
            String r = cl.getComponentType() + "[]{";
            for(int i = 0; i < Array.getLength(obj); i++) {
                if(i > 0) r += ",";
                //获取数组元素
                Object val = Array.get(obj, i);
                //如果数组元素是基本类型，则返回值本身，否则返回toString()方法
                if(cl.getComponentType().isPrimitive()) r += val;
                else r += toString(val);
            }
            return r + "}";
        }
        //获取类型名称
        String r = cl.getName();
        //获取对象所有字段(包括父类)
        do {
            r += "[";
            //获取对象所有字段
            Field[] fields = cl.getDeclaredFields();
            //设置字段可访问
            //报错：Unable to make field private static final long java.util.ArrayList.serialVersionUID accessible: module java.base does not "opens java.util" to unnamed module @3d075dc0
            AccessibleObject.setAccessible(fields, true);
            for(Field f : fields) {
                //f.getModifiers() 返回该属性的修饰符，Modifier.isStatic（）判断是否是静态字段
                if(!Modifier.isStatic(f.getModifiers())) {
                    if(!r.endsWith("[")) r += ",";
                    r += f.getName() + "=";
                    try {
                        //获取字段类型
                        Class<?> t = f.getType();
                        //获取字段值
                        Object val = f.get(obj);
                        //如果字段是基本类型，则返回字段值，否则返回toString()方法
                        if(t.isPrimitive()) r += val;
                        else r += toString(val);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            //获取对象父类
            cl = cl.getSuperclass();
        } while(cl.getSuperclass() != null);
        return r;
    }
}
