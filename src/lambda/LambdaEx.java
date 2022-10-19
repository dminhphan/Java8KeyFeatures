package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Drawable {
    void draw(int x, int y);
    

}

public class LambdaEx {

    public static void main(String[] args) {
        // Without lambda
        Drawable d1 = new Drawable() {
            @Override
            public void draw(int x, int y) {
                System.out.println("Drawing: (" + x + ", " + y + ")");
            }
        };
        d1.draw(1, 2);
        // With lambda
        Drawable d2 = (a, b) -> System.out.println("Drawing: (" + a + ", " + b + ")");
        d2.draw(5, 10);

        // Lambda in collection
        List<String> names = new ArrayList<>(Arrays.asList("AA", "BB", "CC", "DD"));
        String name = names.stream().filter(x -> "BB".equals(x)).findAny().orElse("");
        System.out.println(name);
    }

}
