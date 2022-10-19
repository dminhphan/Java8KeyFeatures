package method.references;

import java.util.Arrays;
import java.util.List;

@FunctionalInterface
interface Drawable {
    void draw(int x, int y);
}

class Drawing {
    public Drawing() {
        System.out.println("No argument");
    }

    public Drawing(int x, int y) {
        System.out.println("Constructor reference: " + "(" + x + ", " + y + ")");
    }

    public static void drawingStatic(int x, int y) {
        System.out.println("Static reference: " + "(" + x + ", " + y + ")");
    }

    public void drawingInstance(int x, int y) {
        System.out.println("Instance reference: " + "(" + x + ", " + y + ")");
    }
}

public class MethodReferencesEx {
    public static void main(String[] args) {
        // Static
//        Drawable d1 = (x, y) -> Drawing.drawingStatic(x, y);
        Drawable d1 = Drawing::drawingStatic;
        d1.draw(1, 2);
        // Instance method of a particular object
        Drawing drawing = new Drawing();
        Drawable d2 = drawing::drawingInstance;
        d2.draw(3, 4);
        // Instance method of an arbitrary object of a particular type
        List<Integer> numbers = Arrays.asList(15, 33, 59, 24, 40);
        // sort with lambda
        numbers.sort((i1, i2) -> i1.compareTo(i2));
        // sort with method reference
        numbers.sort(Integer::compareTo);

        numbers.forEach(System.out::println);
        // Constructor (which constructor?)
        Drawable d3 = Drawing::new;
        d3.draw(5, 6);
    }
}
