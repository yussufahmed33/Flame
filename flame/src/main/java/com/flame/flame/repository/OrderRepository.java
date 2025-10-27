package com.flame.flame.repository;

import com.flame.flame.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel,Integer> {

    Optional<OrderModel> findAllById(Integer integer);
}
