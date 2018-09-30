package com.fanshe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Car2 {
    String carname;
    public Car2() {
    }

    public Car2(String carname) {
        this.carname = carname;
    }
}

class Farther2 {
    public int f_public;
    private int f_private;

    public void mf_public() {
        System.out.println("mf_public");
    }

    private void mf_private() {
        System.out.println("mf_private");
    }

}

class Son2 extends Farther2 {
    int s_default;
    private String s_private;
    protected float s_protected;
    public List<String > strs;
    public List<Car2> cars;
    public HashMap<String,Car2> sc;
    public HashMap<Integer, String> map;
    public HashSet<String > set;

    void ms_default() {
        System.out.println("ms_default");
    }

    private void ms_private() {
        System.out.println("ms_priavte");
    }

    protected void ms_protected() {
        System.out.println("ms_protected");
    }

}

public class Test2 {
    public static void main(String[] args) {
        //获取field的类型
        Class cls = Son2.class;
        Field[] fields = cls.getFields();
        System.out.println("getFields:");
        for (Field f : fields) {
            //获取field的名称和类型（2中获取类型的方法，getGenericType()可以获取到泛型的信息）
            System.out.println("f.getName():"+f.getName());
            System.out.println("f.getType():"+f.getType());
            System.out.println("f.getGenericType():"+f.getGenericType());
            //获取field的修饰符
            System.out.println("f.getModifiers():"+f.getModifiers());
            System.out.println("---------------------");
        }
    }
}
