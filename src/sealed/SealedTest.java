package sealed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

sealed interface JSONValue permits JSONObject, JSONArray, JSONPrimitive {
    //default方法，使得接口可以包含带有方法体的默认实现。实现类可以选择是否重写default方法。如果不重写，默认实现将被继承。
     default String type(){
        if(this instanceof JSONObject) return "array";
        else if(this instanceof JSONArray) return "object";
        else if(this instanceof JSONString) return "string";
        else if(this instanceof JSONNumber) return "number";
        else if(this instanceof JSONBoolean) return "boolean";
        else if(this instanceof JSONNull) return "null";
        else return "unknown";

    }
}

final class JSONArray extends ArrayList<JSONValue> implements JSONValue{ }

final class JSONObject extends HashMap<String, JSONValue> implements JSONValue{
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("{");
        for(Map.Entry<String, JSONValue> entry : entrySet()){
            if (result.length() > 1)result.append( ",");
            result.append(" \"");
            result.append(entry.getKey());
            result.append("\": ");
            result.append(entry.getValue());
        }
        result.append("}");
        return result.toString();
    }
}

sealed interface JSONPrimitive extends JSONValue permits JSONString, JSONNumber, JSONBoolean, JSONNull { }

record JSONNumber(double value) implements JSONPrimitive {
    public String toString(){
        return " " + value ;
    }
}

record JSONString(String value) implements JSONPrimitive {
    public String toString(){
        return "\"" + value.translateEscapes() + "\"";
    }
}

enum JSONBoolean implements JSONPrimitive {
    TRUE(true), FALSE(false);
    private final boolean value;
    JSONBoolean(boolean value){
        this.value = value;
    }
    public String toString(){
        return super.toString().toLowerCase();
    }
}

enum JSONNull implements JSONPrimitive {
    INSTANCE;
    public String toString(){
        return "null";
    }
}

public class SealedTest {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", new JSONString("Mike"));
        jsonObject.put("age", new JSONNumber(30));
        jsonObject.put("isStudent", JSONBoolean.TRUE);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONNumber(5.99));
        jsonArray.add(JSONNull.INSTANCE);

        jsonObject.put("grades", jsonArray);
        System.out.println(jsonObject);
        System.out.println(jsonObject.type());

    }
}