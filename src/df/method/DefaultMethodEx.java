package df.method;

@FunctionalInterface
interface Drawable {
    void draw(int x, int y);

    static void helloStatic() {
        System.out.println("Hello static method");
    }

    default void helloDefault() {
        System.out.println("Hello default method");
    }
}

interface MyInterface {
    default void helloDefault() {
        System.out.println("Same method");
    }
}

class User implements Drawable, MyInterface {


    @Override
    public void draw(int x, int y) {
    }

    @Override
    public void helloDefault() {
        ///
        MyInterface.super.helloDefault();
    }

}

public class DefaultMethodEx {

    public static void main(String[] args) {
        Drawable d = (x, y) -> {
        };
        d.helloDefault();
        Drawable.helloStatic();
    }

}
