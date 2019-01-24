package demo;

import com.alibaba.fastjson.JSONObject;
//这个例子表示，如果不set，就get的话，得到的是null，向jsonObject里面put一个value为null,也是又三项的
//就是那个jsonObject是有三个键值对的，你打印出来没有是打印的问题，调试一下就能看到是三项了。
public class TestGet {
    public static void main(String[] args) {
        Member member=new Member();
        member.setA(1);
        System.out.println(member.getA());
        System.out.println(member.getB());
        System.out.println(member.getC());
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("key1",member.getC());
        jsonObject.put("key2",member.getB());
        jsonObject.put("key3",null);
        System.out.println(jsonObject.toJSONString());
    }
}
class Member{
    int a;
    int b;
    String c;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}