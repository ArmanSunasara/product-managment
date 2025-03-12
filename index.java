import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// Class to represent a product
class Product {
    private String name;
    private double price;
    private int productId;

    public Product(String name, double price, int productId) {
        this.name = name;
        this.price = price;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Price: $" + String.format("%.2f", price) + ", ID: " + productId;
    }
}

// Main class for product management
public class index {
    private static ArrayList<Product> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void addProduct() {
        try {
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter product price: ");
            double price;
            try {
                price = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format. Please enter a valid number.");
                return;
            }
            
            System.out.print("Enter product ID: ");
            int productId;
            try {
                productId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format. Please enter a valid integer.");
                return;
            }
            
            inventory.add(new Product(name, price, productId));
            System.out.println("Product added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    public static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int productId;
        try {
            productId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid integer.");
            return;
        }
        
        Iterator<Product> iterator = inventory.iterator();
        boolean found = false;
        
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() == productId) {
                iterator.remove();
                found = true;
                System.out.println("Product deleted successfully!");
                break;
            }
        }
        
        if (!found) {
            System.out.println("Product not found!");
        }
    }

    public static void viewProducts() {
        if (inventory.isEmpty()) {
            System.out.println("No products in the inventory.");
        } else {
            System.out.println("\nList of Products:");
            for (Product product : inventory) {
                System.out.println(product);
            }
        }
    }

    public static void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int productId;
        try {
            productId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid integer.");
            return;
        }
        
        boolean found = false;
        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            if (product.getProductId() == productId) {
                found = true;
                
                System.out.print("Enter new product name (leave blank to keep current): ");
                String name = scanner.nextLine();
                
                System.out.print("Enter new product price (leave blank to keep current): ");
                String priceInput = scanner.nextLine();
                
                // Create updated product
                String updatedName = name.isEmpty() ? product.getName() : name;
                double updatedPrice = product.getPrice();
                
                if (!priceInput.isEmpty()) {
                    try {
                        updatedPrice = Double.parseDouble(priceInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price format. Price not updated.");
                    }
                }
                
                // Replace the product
                inventory.set(i, new Product(updatedName, updatedPrice, productId));
                System.out.println("Product updated successfully!");
                break;
            }
        }
        
        if (!found) {
            System.out.println("Product not found!");
        }
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nProduct Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. View Products");
            System.out.println("4. Update Product");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Please enter a number.");
                choice = 0;
                continue;
            }

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    viewProducts();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);
        
        scanner.close();
    }
}  