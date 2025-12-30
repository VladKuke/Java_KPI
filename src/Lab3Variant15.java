import java.util.Arrays;
import java.util.Comparator;

class Clothing {
    private int id;
    private String name;
    private String size;
    private String color;
    private double price;

    public Clothing(int id, String name, String size, String color, double price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Clothing{id=" + id + ", name='" + name + "', size='" + size + "', color='" + color + "', price=" + price + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clothing clothing = (Clothing) o;
        return id == clothing.id &&
                Double.compare(clothing.price, price) == 0 &&
                name.equals(clothing.name) &&
                size.equals(clothing.size) &&
                color.equals(clothing.color);
    }
}

public class Lab3Variant15 {

    public static void main(String[] args) {
        Clothing[] clothes = new Clothing[] {
                new Clothing(1, "T-Shirt", "M", "White", 19.99),
                new Clothing(2, "Jeans", "L", "Blue", 49.99),
                new Clothing(3, "Jacket", "XL", "Black", 89.99),
                new Clothing(4, "Sneakers", "42", "Red", 49.99),
                new Clothing(5, "Hat", "OneSize", "Grey", 15.50)
        };

        System.out.println("Original Array:");
        for (Clothing c : clothes) {
            System.out.println(c);
        }
        System.out.println("--------------------------------------------------");

        Arrays.sort(clothes, Comparator.comparingDouble(Clothing::getPrice)
                .thenComparing(Comparator.comparing(Clothing::getName).reversed()));

        System.out.println("Sorted Array (Price ASC, Name DESC):");
        for (Clothing c : clothes) {
            System.out.println(c);
        }
        System.out.println("--------------------------------------------------");

        Clothing target = new Clothing(3, "Jacket", "XL", "Black", 89.99);
        boolean found = false;

        System.out.println("Searching for: " + target);
        for (Clothing c : clothes) {
            if (c.equals(target)) {
                System.out.println("Found identical object: " + c);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Object not found.");
        }
    }
}