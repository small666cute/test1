package java8test;

import java.util.function.Supplier;

public class Test1 {
    private interface Defaulable{
        default String notRequired(){
            return "default implementation";
        }
    }
    private static class DefaultableImpl implements Defaulable{

    }
    private static class OverridableImpl implements Defaulable{
        @Override
        public String notRequired(){
            return "overridden implementation";
        }
    }
    private interface DefaulableFactory{
        //Supplier也是一个函数式接口
        static Defaulable create(Supplier<Defaulable> supplier){
            return supplier.get();
        }
    }

    public static void main(String[] args) {
        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());
        defaulable=DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());
    }
}
