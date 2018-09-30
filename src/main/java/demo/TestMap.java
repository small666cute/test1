package demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.Map;

public class TestMap {
    private final static String PRE0086 = "0086-";
    public static void main(String[] args) {
        JSONObject dataObj=new JSONObject();
        dataObj.put("phone","e79b154da2a1382f061534a82842d3d8");
        Map<String,String> map= Maps.newHashMap();
       /* map.put("phoneNumber",PRE0086 + "123");
        map.put("null",null);
        map.put("phone",PRE0086 + dataObj.getString("phone"));
        //System.out.println(map.get("phone"));
        System.out.println("phone".substring(1,2));
        System.out.println(map.get("oooo"));
        System.out.println(map.get("null"));*/
       map.put("is",null);
        System.out.println(map.get("i"));
    }
}
