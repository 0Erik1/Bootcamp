package com.bootcamp.core.models;

public class ProductData {
    private String name;
    private String sku;
    private String price;

    public ProductData() {} // Construtor padrão

    public ProductData(String name, String sku, String price) {
        this.name = name;
        this.sku = sku;
        this.price = price;
    }

    public String getName() { return name; }
    public String getSku() { return sku; }
    public String getPrice() { return price; }
}