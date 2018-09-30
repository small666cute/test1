package com.zhujie;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@TestAnnotation
public class Test {
}

//定义了一个注解叫test2，它被@Inherited注解了，所以因为A被@Test2注解了，继承A的B也有了test2这个注解了
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface Test2 {
}

@Test2
class A {
}

class B extends A {
}

//@repeatable注解的注解Person表示，这个注解（Person）可以多次注解

@interface Persons {
    //这个玩意长的像方法，但表现的就是个变量
    Person[] value();
}

@Repeatable(Persons.class)
@interface Person {
    String role() default "";
}

@Person(role = "artist")
@Person(role = "coder")
@Person(role = "pm")
class SuperMan {

}

//
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation2 {
    int id() default -1;

    String msg() default "1";
}

@TestAnnotation2(id = 3, msg = "1 zhujie")
class Test3 {

}

@TestAnnotation2
class Test4 {

}

//
class Hero {
    @Deprecated
    public void say() {
        System.out.println("noting to say");
    }

    public void speak() {
        System.out.println("say is a fool");
    }
}

class Test5 {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Hero hero = new Hero();
        hero.say();
        hero.speak();
    }
}

class Test6 {
    public static void main(String[] args) {
        boolean hasAnnotation = Test3.class.isAnnotationPresent(TestAnnotation2.class);
        if (hasAnnotation) {
            TestAnnotation2 testAnnotation = Test3.class.getAnnotation(TestAnnotation2.class);
            System.out.println("id:" + testAnnotation.id());
            System.out.println("msg:" + testAnnotation.msg());
        }

    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface Check {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface Perform {

}

@TestAnnotation2(msg = "hello")
class Test7 {
    @Check(value = "hi")
    int a;

    @Perform
    public void testMethod() {
    }

    @SuppressWarnings("deprecation")
    public void test1() {

    }

    public static void main(String[] args) {
        boolean hasAnnotation = Test7.class.isAnnotationPresent(TestAnnotation2.class);
        if (hasAnnotation) {
            TestAnnotation2 testAnnotation2 = Test7.class.getAnnotation(TestAnnotation2.class);
            System.out.println("id:" + testAnnotation2.id());
            System.out.println("msg:" + testAnnotation2.msg());
        }
        try {
            Field a = Test7.class.getDeclaredField("a");
            a.setAccessible(true);
            Check check = a.getAnnotation(Check.class);
            if (check != null) {
                System.out.println("check value:" + check.value());
            } else {
                System.out.println("check is null");
            }
            Method testMethod = Test7.class.getDeclaredMethod("testMethod");
            if (testMethod != null) {
                Annotation[] ans = testMethod.getAnnotations();
                for (int i = 0; i < ans.length; i++) {
                    System.out.println("testmethod annotation:" + ans[i].annotationType().getCanonicalName());
                }
            } else {
                System.out.println("testMethod is null");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}