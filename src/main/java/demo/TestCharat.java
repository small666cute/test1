package demo;

public class TestCharat {
    public static void main(String[] args) {
        String str="12819";
        System.out.println(str.charAt(0));
        char char1='1';
        System.out.println(char1==(str.charAt(0)));
        if (char1==(str.charAt(0))) {
           //测试
            System.out.println(str.substring(0, 3));
        }
    }
}
