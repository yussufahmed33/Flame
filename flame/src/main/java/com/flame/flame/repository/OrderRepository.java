package com.flame.flame.repository;

import com.flame.flame.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel,Integer> {
}
