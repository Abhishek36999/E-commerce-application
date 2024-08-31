import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItems;
    private double totalAmount;

    public Cart() {
        this.cartItems = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addProduct(Product product, int quantity) {
        boolean productExists = false;
        for (Product p : cartItems) {
            if (p.getId() == product.getId()) {
                p.setQuantity(p.getQuantity() + quantity);
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            product.setQuantity(quantity);
            cartItems.add(product);
        }
        totalAmount += product.getPrice() * quantity;
    }

    public void removeProduct(int productId) {
        Product toRemove = null;
        for (Product p : cartItems) {
            if (p.getId() == productId) {
                totalAmount -= p.getPrice() * p.getQuantity();
                toRemove = p;
                break;
            }
        }
        if (toRemove != null) {
            cartItems.remove(toRemove);
        }
    }

    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Your Cart:");
            System.out.println("ID\tName\t\tPrice\tQuantity");
            for (Product p : cartItems) {
                p.displayProduct();
            }
            System.out.println("Total: " + calculateTotal());
        }
    }

    public double calculateTotal() {
        return totalAmount;
    }

    public void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Add items before checkout.");
        } else {
            System.out.println("Checkout Summary:");
            viewCart();
            System.out.println("Thank you for your purchase!");
            cartItems.clear();
            totalAmount = 0.0;
        }
    }
}