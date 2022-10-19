package method.references;

import java.util.ArrayList;
import java.util.List;

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int comparePerson(Person other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

}

public class ComparingEx {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Nguyen Van A", 23));
        list.add(new Person("Tran Van B", 24));
        list.add(new Person("Le Van C", 25));
        list.add(new Person("Bui Van D", 26));

        // Method references
        // Instance method of an arbitrary object of a particular type
        list.sort(Person::comparePerson);

        list.forEach(System.out::println);
    }
}
