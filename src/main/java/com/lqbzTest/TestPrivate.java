package com.lqbzTest;
//在这里我想看看private,public这四个修饰符，在继承的时候有什么约束，
// 得到的结果是只能>=父类的权限，
public class TestPrivate {
    private class father{
        void func(){
            System.out.println("");
        }
    }
    private class son extends father{
        @Override
        void func(){

        }
    }
}
