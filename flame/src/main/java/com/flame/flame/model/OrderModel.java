package com.flame.flame.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class OrderModel {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private UserModel user;
    private int productId;
    private int quantity;
    private LocalDateTime orderDate;
    @Column(name = "totalprice")
    private double totalPrice;
    private double deliveryFee;
    private String address;
    private String status;
    @Column(name = "governorateName")
    private String governorateName;
    private String city;
    private String postalCode;
    private String phone;
    private String paymentMethod;
    private String productImg;
}
