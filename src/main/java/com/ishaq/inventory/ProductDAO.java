package com.ishaq.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public void addProduct(Product product) {

        String sql = """
                INSERT INTO products (name, description, category, price, quantity)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getCategory());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getQuantity());

            statement.executeUpdate();

            System.out.println("Product added successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to add product.");
            e.printStackTrace();
        }
    }
    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                );

                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch products.");
            e.printStackTrace();
        }

        return products;
    }
    public Product getProductById(int id) {

        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getString("category"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("quantity")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Failed to find product.");
            e.printStackTrace();
        }

        return null;
    }
    public void updateProduct(Product product) {

        String sql = """
            UPDATE products
            SET name = ?, description = ?, category = ?, price = ?, quantity = ?
            WHERE id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getCategory());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getQuantity());
            statement.setInt(6, product.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update product.");
            e.printStackTrace();
        }
    }
    public void updateStock(int productId, int newQuantity) {

        String sql = """
            UPDATE products
            SET quantity = ?
            WHERE id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newQuantity);
            statement.setInt(2, productId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Stock updated successfully!");
            } else {
                System.out.println("Product not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update stock.");
            e.printStackTrace();
        }
    }
    public void deleteProduct(int productId) {

        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, productId);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete product.");
            e.printStackTrace();
        }
    }
}
