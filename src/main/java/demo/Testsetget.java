package demo;
class A{
    int a;
    String b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
public class Testsetget {
    public static void main(String[] args) {
        A a=new A();
        a.setA(1);
        System.out.println(a.getA());
        System.out.println(a.getB());
    }
}
