package com.test1;

import java.util.List;

interface i {
    void talk();
}
class ii implements i{
    public void talk(){
        System.out.println("ii");
    }
}
class iii implements i{
    public void talk(){
        System.out.println("iii");
    }
}
class Test {
    i temp=new iii();
    public void tell(){
        temp.talk();
    }
}
public class Test8{
    public static void main(String[] args) {
        Test t=new Test();
        t.tell();
    }


}
