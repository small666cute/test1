package com.lambda;

import java.util.function.Function;

//练习lambda
public class TestLambda {
}

//一个函数式接口
interface MyNumber {
    Double getValue();
}


class lambdaDemo {
    public static void main(String[] args) {
        MyNumber myNum;
        //必须返回的值是double/Double（和上面的要实现的抽象方法兼容),
        myNum = () -> 123.45;
        System.out.println("()->123.45:" + myNum.getValue());
        myNum = () -> Math.random() * 100;
        System.out.println("()->Math.random()*100:" + myNum.getValue());
        System.out.println("()->Math.random()*100:" + myNum.getValue());
        /*myNum =() -> new Integer(123);
        System.out.println("() -> new Integer(123):"+myNum.getValue());*/
    }
}

//下面这个lambda练习给个参数
interface NumericTest {
    boolean test(int n);
}

class lambdaDemo2 {
    public static void main(String[] args) {
        NumericTest isEven = (n) -> (n % 2) == 0;
        //或者这样  NumericTest isEven = (int n) -> (n % 2) == 0;   NumericTest isEven = n -> (n % 2) == 0; 都是可以的
        //int/Integer都是兼容的
        if (isEven.test(new Integer(10))) System.out.println("10 is even");
        if (!isEven.test(9)) System.out.println("9 is not even");
        NumericTest isNonNeg = (n) -> n >= 0;
        if (isNonNeg.test(1)) System.out.println("is is non-negative");
        if (!isNonNeg.test(-1)) System.out.println("-1 is negative");
    }
}

//下面这个例子练习两个参数
interface NumericTest2 {
    boolean test(int n, int d);
}

class lambdaDemo3 {
    public static void main(String[] args) {
        NumericTest2 isFactor = (n, d) -> (n % d) == 0;
        if (isFactor.test(10, 2))
            System.out.println("2 is a factor of 10");
        //如果要指定一个参数的类型的话，就要都指定
        //（int n,int d)可以     （int n,d)不可以
    }
}

//下面是练习  块lambda
interface NumericFunc {
    int func(int n);
}

class BlocklambdaDemo {
    public static void main(String[] args) {
        NumericFunc factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++)
                result = i * result;
            return result;
        };
        //lambda里面的return语句只是会从lambda里面返回到外面，不会返回到更外面
        System.out.println("the factoral of 3 is :" + factorial.func(3));
        System.out.println("the factoral of 5 is :" + factorial.func(5));
    }
}

interface StringFunc1 {
    String func(String n);
}

class BlocklambdaDemo2 {
    public static void main(String[] args) {
        StringFunc1 reverse = (str) -> {
            String result = "";
            int i;
            for (i = str.length() - 1; i >= 0; i--)
                //因为知道str的类型是String,所以才能调用charAt(i)方法
                result += str.charAt(i);
            return result;
        };
        System.out.println("lambda reversed is " + reverse.func("lambda"));
    }
}

//下面的例子是泛型函数式接口
//接受一个任何一个类型的参数，并返回一个相同类型的值
interface SomeFunc<T> {
    T func(T t);
}

class GenericFunctionalInterfaceDemo {
    public static void main(String[] args) {
        SomeFunc<String> reverse = (str) -> {
            String result = "";
            int i;
            for (i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }
            return result;
        };
        System.out.println("lambda reversed is " + reverse.func("lambda"));
        SomeFunc<Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i < n; i++) {
                result = i * result;
            }
            return result;
        };
        System.out.println("the factoral of 3 is " + factorial.func(3));
    }
}

//将lambda表达式作为函数的参数
interface StringFunc2 {
    String func(String n);
}

class lambasAsArgumentsDemo {
    static String stringOp(StringFunc2 sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "lambdas add power to java";
        String outStr;
        System.out.println("here is input string: " + inStr);
        //(str) -> str.toUpperCase()是接口StringFunc2的一个实例，就相当于
        //StringFunc2 temp=(str) -> str.toUpperCase();
        //然后再
        //outStr = stringOp(temp, inStr);
        outStr = stringOp((str) -> str.toUpperCase(), inStr);
        System.out.println("the string in uppercase: " + outStr);
        outStr = stringOp((str) -> {
            String result = "";
            int i;
            for (i = 0; i < str.length(); i++) {
                if (str.charAt(i) != ' ')
                    result += str.charAt(i);
            }
            return result;
        }, inStr);
        System.out.println("the string with spaces removed: " + outStr);
        StringFunc2 reverse = (str) -> {
            String result = " ";
            int i;
            for (i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }
            return result;
        };
        System.out.println("the string reversed: " + stringOp(reverse, inStr));
    }
}

//lambda表达式抛出异常
interface DoubleNumericArrayFunc {
    //这儿必须抛出异常，lambda表达式里面才能抛出异常，不然就不兼容了
    double func(double[] n) throws EmptyArrayException;
}

class EmptyArrayException extends Exception {
    EmptyArrayException() {
        super("Array Empty");
    }
}

class lambdaExceptionDemo {
    public static void main(String[] args) throws EmptyArrayException {
        double[] values = {1.0, 2.0, 3.0, 4.0};
        //此处的n的类型是根据上下文推断出来的，所以不必显式声明double n[],如果写n[]则是错误的
        DoubleNumericArrayFunc average = (n) -> {
            double sum = 0;
            if (n.length == 0) {
                throw new EmptyArrayException();
            }
            for (int i = 0; i < n.length; i++) {
                sum += n[i];
            }
            return sum / n.length;
        };
        System.out.println("the average is :" + average.func(values));
        System.out.println("the average is :" + average.func(new double[0]));
    }
}

//lambda表达式里使用外层作用域的局部变量，使用类的实例变量
interface MyFunc {
    int func(int n);
}

class VarCapture {
    static int test1 = 0;
    int test2 = 0;

    public static void main(String[] args) {
        int num = 10;
        VarCapture testVar = new VarCapture();
        MyFunc mylambda = (n) -> {
            //这儿不会出错，因为是类的static变量
            test1++;
            //这儿也不会出错，因为是类的实例变量
            testVar.test2++;
            //这儿会出错
            //num++;
            int v = num + n;
            //这里有这一条会出错，因为一旦在lambda表达式里使用了外层作用域的局部变量，这个变量就必须是实质上的final变量了
            //也就是说无论在任何地方，lambda表达式里面还是外面,都不能再修改这个变量了
            // num++;
            return v;
        };
        //这里有这一条也会出错
        //num=0;
    }
}

//静态方法引用
interface StringFunc3 {
    String func(String n);
}

class MyStringOps {
    //传入一个string,返回一个string
    static String strReverse(String str) {
        String result = "";
        int i;
        for (i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}

class MethodRefDemo {
    //调用一个StringFunc3类型的对象，调用它的func方法，传入一个string,返回一个string
    static String stringOp(StringFunc3 sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "lambdas add power to java";
        String outStr;
        /*将MyStringOps里面的strReverse()方法的引用传递给了stringOp（）方法的第一个参数，因为这个strReverse()方法
        和StringFunc3的函数式接口func()兼容，这个方法提供了StringFunc3的func()的实现，相当于
        StringFunc3 temp = MyStringOps::strReverse;
        outStr = stringOp(temp, inStr);
        相当于一个lambda表达式*/
        outStr = stringOp(MyStringOps::strReverse, inStr);
        System.out.println("original string: " + inStr);
        System.out.println("string reversed: " + outStr);
    }
}

//实例方法引用
class MyStringOps2 {
    //把上面那个静态方法变成了实例方法
    String strReverse(String str) {
        String result = "";
        int i;
        for (i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }
}

class MethodRefDemo2 {
    static String stringOp(StringFunc3 sf, String s) {
        return sf.func(s);
    }

    public static void main(String[] args) {
        String inStr = "lambdas add power to java";
        String outStr;
        //使用MyStringOps2 的实例strOps的实例方法strReverse的引用
        MyStringOps2 strOps = new MyStringOps2();
        outStr = stringOp(strOps::strReverse, inStr);
        System.out.println("original string: " + inStr);
        System.out.println("original string: " + inStr);
        System.out.println("string reversed: " + outStr);
    }
}

//另一种实例方法引用，使用 类名：实例方法
interface MyFunc2<T> {
    //传入两个参数，返回一个boolean
    boolean func(T v1, T v2);
}

class HighTemp {
    private int hTemp;

    HighTemp(int ht) {
        hTemp = ht;
    }

    //下面这个两个方法和MyFunc2<T>的 boolean func(T v1, T v2);兼容，调用对象（this)（左边的hTemp是第一个参数），传递的实参是第二个参数
    //传进去的是两个HighTemp对象，进行比较的是他们的hTemp属性
    //返回当前实例和给定的是否相等
    boolean sameTemp(HighTemp ht2) {
        return hTemp == ht2.hTemp;
    }

    //返回当前实例是否小于给定的
    boolean lessThanTemp(HighTemp ht2) {
        return hTemp < ht2.hTemp;
    }
}

class InstanceMethWithObjectRefDemo {
    //计数的,其中使用MyFunc2<T> 接口类型的对象，调用它的func方法
    static <T> int counter(T[] vals, MyFunc2<T> f, T v) {
        int count = 0;
        for (int i = 0; i < vals.length; i++) {
            //第一个参数匹配调用对象，第二个参数匹配方法指定的参数，就是说，vals[i]相当于是实例的this，而v相当于是ht2,返回的是对
            //他们hTemp属性的比较结果
            if (f.func(vals[i], v)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int count;
        HighTemp[] weekDayHighs = {new HighTemp(89), new HighTemp(82),
                new HighTemp(83), new HighTemp(82),
                new HighTemp(89), new HighTemp(82)};
        //它就自动对照了，好吧就是这么智能
        count = counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(89));
        System.out.println(count + "days had a high of 89");

        HighTemp[] weekDayHighs2 = {new HighTemp(9), new HighTemp(82),
                new HighTemp(8), new HighTemp(82),
                new HighTemp(89), new HighTemp(82)};
        count = counter(weekDayHighs2, HighTemp::sameTemp, new HighTemp(8));
        System.out.println(count + "day had a high of 8");

        count = counter(weekDayHighs, HighTemp::lessThanTemp, new HighTemp(89));
        System.out.println(count + "day had a high less than 89");
        count = counter(weekDayHighs2, HighTemp::lessThanTemp, new HighTemp(19));
        System.out.println(count + "days had a high of less than 19");
    }
}

//泛型中的方法引用
interface MyFunc3<T> {
    int func(T[] vals, T v);
}

class MyArrayOps {
    static <T> int countMatching(T[] vals, T v) {
        int count = 0;
        for (int i = 0; i < vals.length; i++) {
            if (vals[i] == v) {
                count++;
            }
        }
        return count;
    }
}

class GenericMethodRefDemo {
    static <T> int myOp(MyFunc3<T> f, T[] vals, T v) {
        return f.func(vals, v);
    }

    public static void main(String[] args) {
        Integer[] vals = {1, 3, 4, 7, 8, 8, 4, 3};
        String[] strs = {"one", "two", "two"};
        int count;
        count = myOp(MyArrayOps::<Integer>countMatching, vals, 4);
        System.out.println("vals contains " + count + "4s");

        count = myOp(MyArrayOps::<String>countMatching, strs, "two");
        System.out.println("strs contains " + count + "twos");
    }
}

//构造函数引用
interface MyFunc4 {
    //传入int类型的参数，返回MyClass2类型的引用
    MyClass2 func(int n);
}

class MyClass2 {
    private int val;

    //这个构造函数有个int的参数
    public MyClass2(int val) {
        this.val = val;
    }

    //这个就没有
    MyClass2() {
        val = 0;
    }

    public int getVal() {
        return val;
    }
}

class ConstuctorRefDemo {
    public static void main(String[] args) {
        //一个MyFunc4接口类型的对象，因为MyClass2::new这个玩意就相当于一个lambda表达式
        //因为MyFunc4里面的func方法是需要一个int类型的参数的，所以它和有参的那个构造函数是兼容的
        //所以这个被引用的是有参的那一个构造函数，
        //就是这么智能？
        MyFunc4 myClassCons = MyClass2::new;
        //这个就相当于调用了MyClass2(int v),
        MyClass2 mc = myClassCons.func(100);
        System.out.println("val in mc is " + mc.getVal());
    }
}

//下面这个是泛型的构造函数引用
interface MyFunc5<T> {
    MyClass3<T> func(T n);
}

class MyClass3<T> {
    private T val;

    MyClass3(T v) {
        val = v;
    }

    MyClass3() {
        val = null;
    }

    public T getVal() {
        return val;
    }
}

class ConstructorRefDemo2 {
    public static void main(String[] args) {
        MyFunc5<Integer> myClassCons = MyClass3<Integer>::new;
        MyClass3<Integer> mc = myClassCons.func(100);
        System.out.println("val in mc is " + mc.getVal());
    }
}

//但是上面那样使用构造函数没什么意义，所以下面展示一个实际一点的用法
interface MyFunc6<R, T> {
    //接受一个泛型参数，返回另一个泛型参数
    R func(T n);
}

//这是一个泛型类
class MyClass4<T> {
    private T val;

    MyClass4(T v) {
        val = v;
    }

    MyClass4() {
        val = null;
    }

    T getVal() {
        return val;
    }
}

//这不是一个泛型类
class MyClass5 {
    String str;

    MyClass5(String s) {
        str = s;
    }

    MyClass5() {
        str = "";
    }

    String getVal() {
        return str;
    }
}

class ConstructorRefDemo3 {
    //使用这个工厂方法，可以创建任意类型的对象，只要：这个类的构造函数和MyFunc6的func函数兼容
    static <R, T> R myClassFactory(MyFunc6<R, T> cons, T v) {
        return cons.func(v);
    }

    public static void main(String[] args) {
        //把一个构造函数的引用，给MyFunc6类型的对象，MyClass4<Double>是这个func方法要返回的参数，和构造函数返回的对象类型相同
        //Double是传进去的参数
        MyFunc6<MyClass4<Double>, Double> myClassCons = MyClass4<Double>::new;
        MyClass4<Double> mc = myClassFactory(myClassCons, 100.1);
        System.out.println("vals in mc is " + mc.getVal());
        //这个和上面的一样，只是类是上面那个非泛型类
        MyFunc6<MyClass5, String> myClassCons2 = MyClass5::new;
        MyClass5 mc2 = myClassFactory(myClassCons2, "lambda");
        System.out.println("str in mc2 is " + mc2.getVal());
    }
}

//数组的构造函数引用
interface MyArrayCreator<T> {
    T func(int n);
}

class ConstructorRefDemo4 {
    public static void main(String[] args) {
        //MyArrayCreator接受一个MyClass[]类型的参数，返回一个MyClass[]类型的参数
        MyArrayCreator<MyClass[]> myArrayCons = MyClass[]::new;
        //这个func接受的参数一般只有一个Int类型，表示这个数组的大小，
        MyClass[] a = myArrayCons.func(2);
        a[0] = new MyClass(1);
        a[1] = new MyClass(2);
    }
}

//使用预定义的函数式接口
class UseFunctonInterfaceDemo {
    public static void main(String[] args) {
        //Function<T,R>是另一个包里已经有了的函数式接口，它接受类型为T的对象，返回类型为R的对象，包含的方法是apply()
        Function<Integer, Integer> factorial = (n) -> {
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result = i * result;
            }
            return result;
        };
        System.out.println("the factoral of 3 is "+factorial.apply(3));
        System.out.println("the factoral of 5 is "+factorial.apply(5));
    }
}