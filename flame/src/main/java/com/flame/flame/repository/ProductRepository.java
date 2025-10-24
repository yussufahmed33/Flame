package com.flame.flame.repository;

import com.flame.flame.model.ProductModel;
import com.flame.flame.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel,Integer> {
    @Query("SELECT u FROM UserModel u LEFT JOIN FETCH u.products WHERE u.id = :id")
    Optional<UserModel> findByIdWithProducts(@Param("id") int id);

}
