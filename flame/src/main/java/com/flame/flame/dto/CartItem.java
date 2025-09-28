package com.flame.flame.dto;

import com.flame.flame.model.ProductModel;
import lombok.Data;





@Data
public class CartItem {
    private ProductModel product;
    private int quantity;

    public CartItem(ProductModel product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
