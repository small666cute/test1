package demo;

public class Testboolean {
    public static void main(String[] args) {
        Boolean flag=false;
        test(flag);
        System.out.println(flag);
    }
    private static void test(Boolean flag){
        flag=true;
    }
}
