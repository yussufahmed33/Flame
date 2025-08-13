package com.flame.flame.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;


}

