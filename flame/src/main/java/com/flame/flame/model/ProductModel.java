package com.flame.flame.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.List;
//import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
//private List<String> image;
private String image;
private int quantity;
private String category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserModel user;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> images;

//    public void addImage(ProductImage image) {
//        images.add(image);
//        image.setProduct(this);
//    }
}
