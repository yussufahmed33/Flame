package com.flame.flame.repository;

import com.flame.flame.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel,Integer> {

}
