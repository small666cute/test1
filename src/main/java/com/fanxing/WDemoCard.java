package com.fanxing;


//练习?通配符
public class WDemoCard {
    public static void main(String[] args) {
        System.out.println("creating 'long' stack:");
        NumberStack<Long> longStack = new NumberStack<>();//传入Long类型
        longStack.push(5L);
        longStack.push(10L);
        System.out.println("creating 'number' stack:");
        NumberStack<Number> numberStack = new NumberStack<>();//传入Number类型
        numberStack.push(10);
        System.out.println("\ndumping 'long' stack");
        dumpStack(longStack);
        System.out.println("\ndumping 'number'stack");
        dumpStack(numberStack);
    }

    //使用?通配符，这样可以接收任何以T extends Number实例化的栈作为参数，如果是写Number的话就会出错
    // 打印栈
    static void dumpStack(NumberStack<?> stack) {
        for (Number n : stack.getStack()) {
            System.out.println(n);
        }
    }
}

//一个栈，泛型，限定类型：Number类及其子类，栈的大小是5，
class NumberStack<T extends Number> {
    private Number stack[] = new Number[5];
    private int ptr = -1;

    public Number[] getStack() {
        return stack;
    }

    void push(T data) {
        ptr++;
        stack[ptr] = data;
    }

    T pop() {
        return (T) stack[ptr--]
                ;
    }
}

