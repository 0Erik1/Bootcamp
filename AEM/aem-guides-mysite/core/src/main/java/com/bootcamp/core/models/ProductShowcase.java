package com.bootcamp.core.models;

import java.util.List;

public interface ProductShowcase {
    String getSectionTitle();
    List<ProductData> getProducts();
    boolean isEmpty();
}