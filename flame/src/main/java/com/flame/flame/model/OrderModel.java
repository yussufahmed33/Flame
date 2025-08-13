package com.flame.flame.model;

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
    private int userId;
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
}
