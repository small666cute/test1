package demo;

import java.util.HashMap;
import java.util.Map;

public class TestToString {
    public static void main(String[] args) {
        Map<String,Object> map=new HashMap<>();
        map.put("key","value");
        String value=(String)map.get("key");
        String value2=map.get("key").toString();
        System.out.println(value);
        System.out.println(value2);
    }
}
