package stream.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Product {
    String id;
    String name;
    long price;

    public Product(String id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }
}

class Store {
    String id;
    String name;
    List<Product> products;

    public Store(String id, String name, List<Product> products) {
        super();
        this.id = id;
        this.name = name;
        this.products = products;
    }
}

public class StreamEx {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("p1", "Product 1", 2000));
        products.add(new Product("p2", "Product 2", 3000));
        products.add(new Product("p3", "Product 3", 1000));
        products.add(new Product("p4", "Product 4", 6000));
        products.add(new Product("p5", "Product 5", 5000));
        List<Product> anotherProducts = new ArrayList<>();
        anotherProducts.add(new Product("p6", "Product 6", 2000));
        anotherProducts.add(new Product("p7", "Product 7", 8000));
        anotherProducts.add(new Product("p8", "Product 8", 10000));
        // Filter
        List<Product> filterProducts = products.stream().filter(p -> p.price >= 3000).collect(Collectors.toList());
        System.out.println(filterProducts.size());
        // Map
        List<String> productNames = products.stream().map(p -> p.name).collect(Collectors.toList());
        productNames.forEach(System.out::println);
        // FlatMap
        List<Store> stores = new ArrayList<>();
        stores.add(new Store("st1", "Store 1", products));
        stores.add(new Store("st2", "Store 2", anotherProducts));
//         stores.stream().map(x -> x.products).collect(Collectors.toList()).forEach(System.out::println);
        List<Product> allProducts = stores.stream().flatMap(x -> x.products.stream()).collect(Collectors.toList());
        allProducts.forEach(System.out::println);
        // Reduce
        long totalPrice = products.stream().map(p -> p.price).reduce(0l, (x1, x2) -> x1 + x2);
        System.out.println("Total price: " + totalPrice);
        Optional<Long> maxPrice = products.stream().map(p -> p.price).reduce((x1, x2) -> x1 > x2 ? x1 : x2);
        maxPrice.ifPresent(System.out::println);
        Optional<Long> minPrice = products.stream().map(p -> p.price).reduce((x1, x2) -> x1 < x2 ? x1 : x2);
        minPrice.ifPresent(System.out::println);
        long count = products.stream().map(p -> p.price).reduce(0l, (i, p) -> i + 1);
        System.out.println(count);
    }

}
