package com.flame.flame.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class ProductModel {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
private String name;
private String description;
private double price;
private String image;
private int quantity;
private String category;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductModel> products = new HashSet<>();

}
