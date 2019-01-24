package demo;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections4.CollectionUtils;

public class TestCollection {
    public static void main(String[] args) {
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(null);
        jsonArray.add(null);
        jsonArray.add(null);
        for(int i=0;i<jsonArray.size();i++){
            System.out.println(jsonArray.get(i));
        }
        System.out.println(CollectionUtils.isEmpty(jsonArray));
    }
}
