package demo;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;

public class TestContinue {
    public static void main(String[] args) {
        List<String > list= Lists.newArrayList();
        list.add("0");
        list.add("1");
        list.add("2");
        for(String str:list){
            if("2".equals(str)){
                continue;
            }
            System.out.println(str);
        }
        JSONArray jsonArray=new JSONArray();
        jsonArray.add(null);
        System.out.println(jsonArray);
        System.out.println(CollectionUtils.isEmpty(jsonArray));
    }
}
