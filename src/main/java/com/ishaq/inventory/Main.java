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
                    double price = scanner.nextDouble();

                    System.out.print("Enter quantity: ");
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
                    double price = scanner.nextDouble();

                    System.out.print("Enter new quantity: ");
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
                    int productId = scanner.nextInt();

                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();

                    productService.updateStock(productId, newQuantity);

                    break;
                }

                case 6: {

                    System.out.print("Enter product ID to delete: ");
                    int productId = scanner.nextInt();

                    productService.deleteProduct(productId);

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