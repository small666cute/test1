package com.fanshe;

//开始学习内省和反射
public class DynamicInvoker {
    public static void main(String[] args) {
        DynamicInvoker app = new DynamicInvoker();
        app.printGreeting("jonny", 5);
        System.out.println("\ndynamic invocaton of printGreeting method");
        try {
            app.getClass().getMethod("printGreeting", new Class[]{
                    Class.forName("java.lang.String"), Integer.TYPE}).invoke(app, new Object[]{"sanjay", new Integer(3)});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void printGreeting(String name, int numberOfTimes) {
        for (int i = 0; i < numberOfTimes; i++) {
            System.out.println("hello " + name);
            String test = new String("test8777777");
        }
    }
}
