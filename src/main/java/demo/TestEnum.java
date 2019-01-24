package demo;

import java.util.Random;

public class TestEnum {
    public static void main(String[] args) {
        Fruit f;
        f = Fruit.APPLE;
        for (Fruit f1 : Fruit.values()) {
            System.out.println(f1);
        }
        Fruit f2 = Fruit.valueOf("BANANA");
        System.out.println("f2:" + f2);
        System.out.println(f2.getClass().getName());
        if (f == Fruit.BANANA) {
            System.out.println("banana");
        }
        switch (f) {
            case BANANA:
                System.out.println("banana");
                break;
            case APPLE:
                //System.out.println("apple");
                //System.out.println(Fruit.APPLE);
                break;
            case ORANGE:
                System.out.println("orange");
                break;
        }
    }
}

enum Fruit {
    APPLE, ORANGE, BANANA;
}

enum Apple {
    Jonathan(10), GoldenDel(9), RedDel(12), Winesap(15), Cortland(8);
    private int price;//每种苹果的价钱

    //这是一个构造函数
    Apple(int p) {
        price = p;
    }

    int getPrice() {
        return price;
    }
}

class EnumDemo3 {
    public static void main(String[] args) {
        Apple ap;
        //
        System.out.println("Winesap costs" + Apple.Winesap.getPrice() + "cents.\n");
        //
        System.out.println("所有苹果的价钱");
        for (Apple a : Apple.values()) {
            System.out.println(a + " costs " + a.getPrice() + "cents.");
        }
    }
}

class EnumDemo4 {
    public static void main(String[] args) {
        Apple ap, ap2, ap3;
        //
        System.out.println("这是所有的苹果的常量" + "和他们的ordinal 值");
        for (Apple a : Apple.values()) {
            System.out.println(a + " " + a.ordinal());
        }
        ap = Apple.RedDel;
        ap2 = Apple.GoldenDel;
        ap3 = Apple.RedDel;
        //试一试compare和equal的用法
        if (ap.compareTo(ap2) < 0) {
            System.out.println(ap + " comes before " + ap2);
        }
        if (ap.compareTo(ap2) > 0) {
            System.out.println(ap2 + " comes before " + ap);
        }
        if (ap.compareTo(ap2) == 0) {
            System.out.println(ap + " equals " + ap3);
        }
        System.out.println();
        if (ap.equals(ap2)) {
            System.out.println("error!");
        }
        if (ap.equals(ap3)) {
            System.out.println(ap + " equals " + ap3);
        }
        if (ap == ap3) {
            System.out.println(ap + " == " + ap3);
        }
    }
}

//下面是一个例子
enum Answers {
    NO, YES, MAYBE, LATER, SOON, NEVER
}

class Question {
    Random rand = new Random();

    //返回枚举类 类型的参数，就随机返回一个枚举类型
    Answers ask() {
        int prob = (int) (100 * rand.nextDouble());
        if (prob < 15)
            return Answers.MAYBE;
        else if (prob < 30)
            return Answers.NO;
        else if (prob < 60)
            return Answers.YES;
        else if (prob < 75)
            return Answers.LATER;
        else if (prob < 98)
            return Answers.SOON;
        else
            return Answers.NEVER;
    }
}

class AskME {
    //answer就你传的枚举是什么，就给你打印对应的字符串
    static void answer(Answers result) {
        switch (result) {
            case NO:
                System.out.println("NO");
                break;
            case YES:
                System.out.println("Yes");
                break;
            case MAYBE:
                System.out.println("Maybe");
                break;
            case LATER:
                System.out.println("Later");
                break;
            case SOON:
                System.out.println("Soon");
                break;
            case NEVER:
                System.out.println("Never");
                break;
        }
    }

    public static void main(String[] args) {
        Question q = new Question();
        answer(q.ask());
        answer(q.ask());
        answer(q.ask());
        answer(q.ask());
        answer(q.ask());
    }
}
