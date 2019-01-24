package demo;

public class TestFloor {
    public static void main(String[] args) {
        //System.out.println(Math.floor(-1.01));
        Double d = 0.11;
        if (d instanceof Number) {
            System.out.println(((Number) d).intValue());
        }
        String s="2.1";
        String s2="2";
        System.out.println(s.contains(".")?s.split("\\.")[0]:s);
        System.out.println(s2.contains(".")?s2.split("\\.")[0]:s2);
    }
}
