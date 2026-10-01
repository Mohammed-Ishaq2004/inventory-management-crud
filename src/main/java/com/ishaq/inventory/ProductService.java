package com.ishaq.inventory;

import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }
    public void addProduct(Product product) {

        if (product.getName() == null || product.getName().isBlank()) {
            System.out.println("Product name cannot be empty.");
            return;
        }

        if (product.getPrice() < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }

        if (product.getQuantity() < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        productDAO.addProduct(product);
    }
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }
    public void updateProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            System.out.println("Product name cannot be empty.");
            return;
        }

        if (product.getPrice() < 0) {
            System.out.println("Price cannot be negative.");
            return;
        }

        if (product.getQuantity() < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        productDAO.updateProduct(product);
    }
    public void updateStock(int productId, int newQuantity) {

        if (newQuantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        productDAO.updateStock(productId, newQuantity);
    }
    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }
}