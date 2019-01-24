package demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.*;

public class TestMap {
    private final static String PRE0086 = "0086-";
    public static void main(String[] args) {
       /* JSONObject dataObj=new JSONObject();
        dataObj.put("phone","e79b154da2a1382f061534a82842d3d8");
        Map<String,String> map= Maps.newHashMap();
       *//* map.put("phoneNumber",PRE0086 + "123");
        map.put("null",null);
        map.put("phone",PRE0086 + dataObj.getString("phone"));
        //System.out.println(map.get("phone"));
        System.out.println("phone".substring(1,2));
        System.out.println(map.get("oooo"));
        System.out.println(map.get("null"));*//*
       map.put("is",null);
        System.out.println(map.get("is"));*/
        Map<String ,Object> map=new HashMap<>();
        List<String > list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        for(String str:list){
            System.out.println("当前str="+str);
            map.put(str,str);
            for(String str1:list){
                System.out.println("map:"+map.get(str1));
            }
        }


        //测试key能否为null
        Map map1=new HashMap();
        map1.put(null,"1");
        map1.put("2","3");
        System.out.println(map1.get(null));
        //map怎么用iterator
        Iterator iterator=map1.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry next=(Map.Entry)iterator.next();
            System.out.println("key="+next.getKey());
            System.out.println("value="+next.getValue());
        }

    }
}
