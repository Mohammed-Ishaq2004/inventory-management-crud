package com.ishaq.inventory;

public class ProductDAOTest {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();

        productDAO.deleteProduct(3);
    }
}