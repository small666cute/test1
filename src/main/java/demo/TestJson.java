package demo;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestJson {
    public static void main(String[] args) {
        JSONArray jsonArray=new JSONArray();
        jsonArray.add("1");
        jsonArray.add("2");
        jsonArray.add("3");
        //System.out.println(jsonArray.get(jsonArray.size()-1));
        List<String > list= Lists.newArrayList();
        list.add("1");
        list.add("2");
        //System.out.println(list.get(list.size()-1));
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("1",null);
        jsonObject.put("2","");
        jsonObject.put("3","  ");
        JSONObject jsonObject1=new JSONObject();
        jsonObject.put("4",jsonObject1);
        JSONObject jsonObject2=new JSONObject();
        jsonObject2.put("test","test");
        jsonObject.put("5",jsonObject2);
        /*System.out.println(MapUtils.isEmpty(jsonObject.getJSONObject("1")));
        System.out.println(MapUtils.isEmpty(jsonObject.getJSONObject("2")));
        System.out.println(MapUtils.isEmpty(jsonObject.getJSONObject("3")));
        System.out.println(MapUtils.isEmpty(jsonObject.getJSONObject("4")));
        System.out.println(MapUtils.isEmpty(jsonObject.getJSONObject("5")));
        JSONObject jsonObject3=jsonObject2.getJSONObject("test");
        System.out.println(MapUtils.isEmpty(jsonObject3));*/
        Integer i=new Integer(0);
        //System.out.println(i);
        fun(i);
        //System.out.println(i);
        Aa test=new Aa();
        test.a=0;
        //System.out.println(test.a);
        fun1(test);
        //System.out.println(test.a);
        AtomicInteger ii=new AtomicInteger();
        System.out.println(ii);
        fun2(ii);
        System.out.println(ii);
    }
    static void fun(Integer i){
        //System.out.println(i);
        i=1;
        //System.out.println(i);
    }
    static void fun1(Aa test){
        test.a=1;
    }
    static void fun2(AtomicInteger ii){
        ii.incrementAndGet();
    }
}
class Aa{
    int a;

}
