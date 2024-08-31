import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize products
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", 75000.0, 10));
        products.add(new Product(2, "Smartphone", 25000.0, 20));
        products.add(new Product(3, "Headphones", 1500.0, 50));
        products.add(new Product(4, "Keyboard", 800.0, 30));
        products.add(new Product(5, "Mouse", 500.0, 40));

        // Initialize cart
        Cart cart = new Cart();

        // Main loop
        while (true) {
            System.out.println("\n1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Product List:");
                    System.out.println("ID\tName\t\tPrice\tQuantity");
                    for (Product p : products) {
                        p.displayProduct();
                    }
                    break;
                case 2:
                    System.out.print("Enter Product ID to add to cart: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    boolean productFound = false;
                    for (Product p : products) {
                        if (p.getId() == productId) {
                            if (quantity <= p.getQuantity()) {
                                cart.addProduct(new Product(p.getId(), p.getName(), p.getPrice(), quantity), quantity);
                                p.setQuantity(p.getQuantity() - quantity);
                                System.out.println("Product added to cart.");
                            } else {
                                System.out.println("Insufficient stock available.");
                            }
                            productFound = true;
                            break;
                        }
                    }
                    if (!productFound) {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    System.out.print("Enter Product ID to remove from cart: ");
                    int removeProductId = scanner.nextInt();
                    cart.removeProduct(removeProductId);
                    System.out.println("Product removed from cart.");
                    break;
                case 5:
                    cart.checkout();
                    break;
                case 6:
                    System.out.println("Thank you for shopping with us!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
