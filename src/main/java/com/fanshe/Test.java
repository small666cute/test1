package com.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class Car {
    private String mBand;
    private Color mColor;
    public enum Color {
        RED,
        WHITE,
        BLACK,
        BLUE,
        YELLOR
    }
    public Car() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Car(String mBand) {
        this.mBand = mBand;
    }
    public void drive() {
        System.out.println("di di di,开车了！");
    }
    @Override
    public String toString() {
        return "Car [mBand=" + mBand + ", mColor=" + mColor + "]";
    }

}

//这个Outter和inner类是为了练习三种方法打印的类名不同，以及class前面的修饰符
class Outter {
    class Inner {
        public abstract class TestModifier {

        }
    }
}

//这个是练习两种方法能获得的成员变量(field)
class Farther {
    public int f_public;
    private int f_private;
    public void mf_public() {
        System.out.println("mf_public");
    }
    private void mf_private() {
        System.out.println("mf_private");
    }

    public Farther() {
        super();
    }

    public Farther(int f_public, int f_private) {
        this.f_public = f_public;
        this.f_private = f_private;
    }

}

class Son extends Farther {
    int s_default;
    private String s_private;
    protected float s_protected;
    void ms_default(){
        System.out.println("ms_default");
    }
    private void ms_private(int n){
        System.out.println("ms_priavte");
    }
    protected void ms_protected(){
        System.out.println("ms_protected");
    }

    public Son() {
        super();
    }

    protected Son(int s_default, String s_private, float s_protected) {
        super();
        this.s_default = s_default;
        this.s_private = s_private;
        this.s_protected = s_protected;
    }
}

//进行测试的类
public class Test {
    public static void main(String[] args) {
        Car car = new Car();
        //根据实例，getClass()
        Class class1 = car.getClass();
        //根据类名，.class
        Class class2 = Car.class;
        Class class3 = int.class;
        System.out.println(class1);
        System.out.println(class2);
        System.out.println(class3);
        //根据虚拟机里是否加载，寻找类名，Class.forName
        try {
            Class class4 = Class.forName("com.fanshe.Test");
            System.out.println(class4);
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
        System.out.println(class1.getName());
        System.out.println(class1.getCanonicalName());
        System.out.println(class1.getSimpleName());

        //数组的情况
        Class class5 = new Car[]{}.getClass();
        System.out.println(class5.getName());
        System.out.println(class5.getCanonicalName());
        System.out.println(class5.getSimpleName());

        //getname的三种方法
        Class class6 = Outter.Inner.class;
        System.out.println("Outter.Inner.class:getName():" + class6.getName());
        System.out.println("Outter.Inner.class:getSimpleName():" + class6.getSimpleName());
        System.out.println("Outter.Inner.class:getCanonicalName():" + class6.getCanonicalName());
        //同上，数组的情况
        Class class7 = new Outter.Inner[][][][]{}.getClass();
        System.out.println("new Outter.Inner[][][][]{}.getClass():getName():" + class7.getName());
        System.out.println("new Outter.Inner[][][][]{}.getClass():getSimpleName():" + class7.getSimpleName());
        System.out.println("new Outter.Inner[][][][]{}.getClass():getCanonicalName():" + class7.getCanonicalName());

        //内部匿名类的getname三种方法
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        };
        Class class8 = run.getClass();
        System.out.println("run.getClass():getName():" + class8.getName());
        System.out.println("run.getClass():getSimpleName():" + class8.getSimpleName());
        System.out.println("run.getClass():getCanonicalName():" + class8.getCanonicalName());

        //获取修饰符Modifiers
        int mod = Outter.Inner.TestModifier.class.getModifiers();
        System.out.println("modifiers value:" + Outter.Inner.TestModifier.class.getModifiers());
        System.out.println("modifiers :" + Modifier.toString(Outter.Inner.TestModifier.class.getModifiers()));
        System.out.println(Modifier.isAbstract(mod));
        System.out.println(Modifier.isInterface(mod));

        //拿到成员变量 Field
        Class cls = Son.class;
        try {
            Field field = cls.getDeclaredField("f_private");
        } catch (NoSuchFieldException e) {
            System.out.println("getDeclaredField(f_private): " + e.getMessage());
        }
        try {
            Field field = cls.getField("f_private");
        } catch (NoSuchFieldException e) {
            System.out.println("getfield(f_private)： " + e.getMessage());
        } catch (SecurityException e) {
            e.printStackTrace();
            System.out.println("getField(f_private)： " + e.getMessage());
        }
        Field[] field1 = cls.getDeclaredFields();
        System.out.println("getDeclaredFields全部: ");
        for (Field f : field1) {
            System.out.println("getDeclaredFields全部: " + f.getName());
        }
        Field[] field2 = cls.getFields();
        System.out.println("getFields全部: ");
        for (Field f : field2) {
            System.out.println("getFields全部: " + f.getName());
        }
        //获取methods
        Method[] methods = cls.getDeclaredMethods();
        System.out.println("getDeclaredMethods: ");
        for (Method m :methods) {
            System.out.println("getDeclaredMethods全部: " + m.getName());
        }
        Method[] methods2 = cls.getMethods();
        System.out.println("getMethods: ");
        for (Method m : methods2) {
            System.out.println("getMethods全部: " + m.getName());
        }
        //获取constructor
        Constructor[] constructors=cls.getConstructors();
        System.out.println("getConstructors:");
        for(Constructor c:constructors){
            System.out.println("getConstructors全部:"+c.toString());
        }
        constructors = cls.getDeclaredConstructors();
        System.out.println("getDeclaredConstructors:");
        for(Constructor c:constructors){
            System.out.println("getDeclaredConstructors全部:"+c.toString());
        }

    }
}
