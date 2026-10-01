package com.ishaq.inventory;

public class ProductTest {

    public static void main(String[] args) {

        Product product = new Product(
                1,
                "Wireless Mouse",
                "2.4GHz wireless mouse",
                "Electronics",
                799.99,
                25
        );

        System.out.println(product);
    }
}