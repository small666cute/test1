package demo;

import java.util.ArrayList;
import java.util.List;

public class TestRemove {
    public static void main(String[] args) {
        List<String > list=new ArrayList<String >();
        list.add("1");
        System.out.println(list.remove("0"));
        System.out.println(list.get(0));
    }
}
