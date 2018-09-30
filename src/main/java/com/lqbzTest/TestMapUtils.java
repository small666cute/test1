package com.lqbzTest;


import org.apache.commons.collections4.MapUtils;

import java.util.Iterator;
import java.util.Map;
import com.google.common.collect.Maps;

public class TestMapUtils {
    public static void main(String[] args) {
        Map<String,Object> map =Maps.newHashMap();
        map.put("1",new Integer(1));
        map.put("2",new Integer(2));
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key="+key+",value="+value);
        }
        Iterator<Map.Entry<String,Object>> iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String ,Object> iteratorTemp=iterator.next();
            System.out.println(iteratorTemp.getKey()+":"+iteratorTemp.getValue());
        }
    }
}
