package optional;

import java.util.Optional;

class Person {
    private String name;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}

class Address {
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}

class City {
    private Street street;

    public Optional<Street> getStreet() {
        return Optional.ofNullable(street);
    }

    public void setStreet(Street street) {
        this.street = street;
    }

}

class Street {
    private String name;
    private String number;

    public Street(String name, String number) {
        super();
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}

public class OptionalEx {

    public static void main(String[] args) {
        Person person = new Person();
        Address address = new Address();
        person.setAddress(address);
        person.setName("Nguyen Van A");
        City city = new City();
        address.setCity(city);
        Street street = new Street("Cong Hoa", "364");
        city.setStreet(street);

        Optional<Person> opt = Optional.ofNullable(person);
        // Non optional
//         String streetName = person.getAddress().getCity().getStreet().getName();
        // map && flatMap
        Optional<String> optName = opt.map(Person::getAddress).map(Address::getCity).flatMap(City::getStreet)
                .map(Street::getName);
        optName.ifPresent(System.out::println);
        // filter
        Optional<Person> filterPerson = opt.filter(p -> p.getName().equals("Nguyen Van A"));
        filterPerson.ifPresent(p -> System.out.println(p.getName()));
        // orElse && orElseGet
        // Set text = null to see the difference
        String text = "some values";
        System.out.println();
        System.out.println("Using orElse:");
        String defaultText = Optional.ofNullable(text).orElse(getDefaultValue());
        System.out.println(defaultText);
        System.out.println("Using orElseGet:");
        defaultText = Optional.ofNullable(text).orElseGet(OptionalEx::getDefaultValue);
        System.out.println(defaultText);
        // orElseThrow
        defaultText = Optional.ofNullable(text).orElseThrow(IllegalArgumentException::new);
    }

    private static String getDefaultValue() {
        System.out.println("Getting Default Value");
        return "Default Value";
    }

}
