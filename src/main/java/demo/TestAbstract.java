package demo;

public class TestAbstract {
    public static void main(String[] args) {
        Son1 son1=new Son1();
        //(Father1)son1.floatValue();
    }
}
abstract class Father1{
    public abstract float floatValue();
}
class Son1 extends Father1{
    @Override
    public float floatValue() {
        return 0;
    }
}
