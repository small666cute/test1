package demo;

public class TestNumber {
    public static void main(String[] args) {
        Double value=1.1111;
        if(value instanceof Number){
            System.out.println(((Number)value).floatValue());
        }
    }
}
