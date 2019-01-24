package demo;

import java.util.Arrays;

public class Temper {

}

enum Meal {
    APPETIZER(Food.Appetizer.class,5),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(),
    COFFEE(Food.Coffee.class);
    private Food[] values;
    private int i;

    public Food[] getValues() {
        return values;
    }

    public int getI() {
        return i;
    }

    private Meal(Class<? extends Food> kind,int i) {
        //通过class对象获取枚举实例
        values = kind.getEnumConstants();
        //values=Food.MainCourse.class.getEnumConstants();
        this.i=i;
    }
    private Meal() {
    }
    private Meal(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }
    public interface Food {
        enum Appetizer implements Food {SALAD, SOUP, SPRING_ROLLS;}

        enum MainCourse implements Food {LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO;}

        enum Dessert implements Food {TIRAMISU, GELATO, BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL;}

        enum Coffee implements Food {BLACK_COFFEE, DECAF_COFFEE, ESPRESSO, LATTE, CAPPUCCINO, TEA, HERB_TEA;}
    }

    public static void main(String[] args) {
        //Meal values[] =Meal.values();
        //System.out.println(Arrays.toString(values));
        System.out.println(Arrays.toString(Meal.APPETIZER.getValues()));
        System.out.println(Meal.APPETIZER.getI());

        System.out.println(Arrays.toString(Meal.MAINCOURSE.getValues()));
        System.out.println(Meal.MAINCOURSE.getI());

        System.out.println(Arrays.toString(Meal.DESSERT.getValues()));
        System.out.println(Meal.DESSERT.getI());

        for(Meal m:Meal.DESSERT.values()){
            System.out.println(m);
        }
    }
}
