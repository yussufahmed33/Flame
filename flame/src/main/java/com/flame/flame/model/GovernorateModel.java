package com.flame.flame.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "governorates")
@Data
public class GovernorateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double deliveryPrice;


}
