package demo;

public class TestInteger {
    public static void main(String[] args) {
        Double d=1.2455;
        System.out.println(((Number)d).intValue());
        Integer integer=124;
        System.out.println(((Number)integer).doubleValue());
        System.out.println(d.toString().contains("."));
        System.out.println(integer.toString().contains("."));

    }
}
