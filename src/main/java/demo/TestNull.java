package demo;

class Person {
    String name;
    Integer phone;

    public Person(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }
}

public class TestNull {
    public static void main(String[] args) {
        Person person = new Person("name");
        int phone = person.getPhone();
        System.out.println(phone);
    }
}


