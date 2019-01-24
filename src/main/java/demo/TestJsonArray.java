package demo;

import com.alibaba.fastjson.JSONArray;

public class TestJsonArray {
    public static void main(String[] args) {
        JSONArray jsonArray =new JSONArray();
        jsonArray.add(null);
        jsonArray.add(null);
        System.out.println(jsonArray.size());
        System.out.println(jsonArray.toJSONString());
    }
}
