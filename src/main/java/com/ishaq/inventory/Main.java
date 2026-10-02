package com.ishaq.inventory;

import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ProductService productService = new ProductService();

        System.out.println("===== Inventory Management System =====");

        while (true) {

            System.out.println("\n1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Find Product by ID");
            System.out.println("4. Update Product");
            System.out.println("5. Update Stock");
            System.out.println("6. Delete Product");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Discard the invalid input
                continue;           // Restart the menu loop
            }

            int choice = scanner.nextInt();

            switch (choice) {

                case 1: {

                    scanner.nextLine();

                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter price: ");

                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid price! Please enter a number.");
                        scanner.nextLine();
                        break;
                    }

                    double price = scanner.nextDouble();

                    System.out.print("Enter quantity: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid quantity! Please enter a whole number.");
                        scanner.nextLine();
                        break;
                    }

                    int quantity = scanner.nextInt();

                    Product product = new Product(
                            0,
                            name,
                            description,
                            category,
                            price,
                            quantity
                    );

                    productService.addProduct(product);

                    break;
                }

                case 2:

                    List<Product> products = productService.getAllProducts();

                    if (products.isEmpty()) {
                        System.out.println("No products found.");
                    } else {
                        for (Product p : products) {
                            System.out.println(p);
                        }
                    }

                    break;

                case 3:{
                    System.out.print("Enter product ID: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid ID! Please enter a whole number.");
                        scanner.nextLine();
                        break;
                    }

                    int id = scanner.nextInt();

                    Product product = productService.getProductById(id);

                    if (product != null) {
                        System.out.println(product);
                    } else {
                        System.out.println("Product not found.");
                    }

                    break;}

                case 4: {

                    System.out.print("Enter product ID to update: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid ID! Please enter a whole number.");
                        scanner.nextLine();
                        break;
                    }

                    int id = scanner.nextInt();
                    scanner.nextLine();

                    Product existingProduct = productService.getProductById(id);

                    if (existingProduct == null) {
                        System.out.println("Product not found.");
                        break;
                    }

                    System.out.print("Enter new product name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter new description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter new category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter new price: ");

                    if (!scanner.hasNextDouble()) {
                        System.out.println("Invalid price! Please enter a number.");
                        scanner.nextLine();
                        break;
                    }

                    double price = scanner.nextDouble();

                    System.out.print("Enter new quantity: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid quantity! Please enter a whole number.");
                        scanner.nextLine();
                        break;
                    }

                    int quantity = scanner.nextInt();

                    Product product = new Product(
                            id,
                            name,
                            description,
                            category,
                            price,
                            quantity
                    );

                    productService.updateProduct(product);

                    break;
                }
                case 5: {

                    System.out.print("Enter product ID: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid ID! Please enter a whole number.");
                        scanner.nextLine();
                        break;
                    }

                    int productId = scanner.nextInt();

                    System.out.print("Enter new quantity: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid quantity! Please enter a whole number.");
                        scanner.nextLine();
                        break;
                    }

                    int newQuantity = scanner.nextInt();

                    productService.updateStock(productId, newQuantity);

                    break;
                }

                case 6: {

                    System.out.print("Enter product ID to delete: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid ID! Please enter a whole number.");
                        scanner.nextLine();
                        break;
                    }

                    int productId = scanner.nextInt();
                    scanner.nextLine();

                    Product product = productService.getProductById(productId);

                    if (product == null) {
                        System.out.println("Product not found.");
                        break;
                    }

                    System.out.println("Product found: " + product);

                    System.out.print("Are you sure you want to delete this product? (yes/no): ");
                    String confirmation = scanner.nextLine();

                    if (confirmation.equalsIgnoreCase("yes")) {
                        productService.deleteProduct(productId);
                    } else {
                        System.out.println("Delete cancelled.");
                    }

                    break;
                }

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}