package com.test1;

import java.util.*;

//这是一些实验程序，看怎么用的
class TestStatic {
    static int a = 1;

    static {
        System.out.println("static daimakuai");
    }

}

class TestTry {
    public static void main(String[] args) {
        System.out.println(new TestTry().testTry());
    }

    int testTry() {
        try {
            System.out.println("第一条");
            int a = 1 / 0;
            System.out.println("第二条");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        } finally {
            System.out.println("finally");
            //return 3;
        }
        // System.out.println("外面");
        //return 4;
    }
}

class TestIterator {
    public static void main(String[] args) {
        //new TestIterator().TestMap();
    }

    void TestList() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        Iterator<String> iterator = list.iterator();
        System.out.println(list);
        /*while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }*/
    }

    void TestSet() {
        Set<String> set = new HashSet<String>();
        set.add("c");
        set.add("d");
        Iterator<String> iterator = set.iterator();
        System.out.println(set);
        /*while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }*/
    }

    void TestMap() {
        Map map = new HashMap();
        TestKey key1 = new TestKey(1, "1ge");
        TestKey key2 = new TestKey(2, "2ge");
        TestValue value1 = new TestValue(1, new String[]{"a", "b", "c"});
        TestValue value2 = new TestValue(2, new String[]{"aa", "bb", "cc"});
        map.put(key1, value1);
        map.put(key2, value2);
        System.out.println(map);
    }
}

class TestKey {
    int key1;
    String key2;

    public TestKey(int key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public String toString() {
        return String.valueOf(key1) + key2;
    }
}

class TestValue {
    int value1;
    String[] value2;

    public TestValue(int value1, String[] value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return String.valueOf(value1) + Arrays.asList(value2);
    }
}


class TestVolatile {
    private int shared = 1;

    public static void main(String[] args) {
        //new TestVolatile().doShared();
    }

    private void doShared() {
        Get g = new Get();
        Set s = new Set();
        new Thread(g).start();
        new Thread(s).start();
    }

    class Get implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++)
                System.out.println(shared);
        }
    }

    class Set implements Runnable {
        @Override
        public void run() {
            //for (int i=0;i<10;i++)
            shared = 2;
        }
    }
}

class TestVolatile2 implements Runnable {
    volatile private int v;

    public int getv() {
        return v;
    }

    public void setv(int v) {
        this.v = v;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(getv());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestVolatile2 tv2 = new TestVolatile2();
        new Thread(tv2).start();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tv2.setv(i);
        }

    }
}

class TestInt {
    public static void main(String[] args) {

        Integer i = 1;
        Integer j = i + 1;
        Integer k = j - 1;

        System.out.println(i == k);
        System.out.println(i.equals(k));
    }
}

class TestInterrupt {
    public static void main(String[] args) {
        Thread wh1 = new Thread(new Wh());
        wh1.start();
        try {
            while (System.in.read() != '\n') {
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        wh1.interrupt();
    }

}

class Wh implements Runnable {
    private boolean flag = true;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            System.out.println("还在运行while");
            //Thread.sleep(1000);

        }
    }
}

class A {
    public String show(D obj) {
        return ("A and D");
    }

    public String show(A obj) {
        return ("A and A");
    }

}

class B extends A {
    public String show(B obj) {
        return ("B and B");
    }

    @Override
    public String show(A obj) {
        return ("B and A");
    }

    @Override
    public String show(D obj) {
        return super.show(obj);
    }
}

class C extends B {
    @Override
    public String show(B obj) {
        return super.show(obj);
    }

    @Override
    public String show(A obj) {
        return super.show(obj);
    }

    @Override
    public String show(D obj) {
        return super.show(obj);
    }
}

class D extends B {
    @Override
    public String show(B obj) {
        return super.show(obj);
    }

    @Override
    public String show(A obj) {
        return super.show(obj);
    }

    @Override
    public String show(D obj) {
        return super.show(obj);
    }
}

class Demo {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        System.out.println("1--" + a1.show(b));
        System.out.println("2--" + a1.show(c));
        System.out.println("3--" + a1.show(d));
        System.out.println("4--" + a2.show(b));
        System.out.println("5--" + a2.show(c));
        System.out.println("6--" + a2.show(d));
        System.out.println("7--" + b.show(b));
        System.out.println("8--" + b.show(c));
        System.out.println("9--" + b.show(d));
    }
}