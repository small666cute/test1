package demo;

public class TestProtect {
    public static void main(String[] args) {

    }
}
class Father{
    private int i;
    protected void func(){
        System.out.println("protect func father");
    }
}
class Son extends Father{
    @Override
    public void func(){
        System.out.println("public func son");
    }
    /*private void func(){
        System.out.println("private func son");
    }*/
}
