package demo;

import com.google.common.collect.Lists;

import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List<String > list= Lists.newArrayList();
        list.add("1");
        list.add("");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.size());
    }
}
