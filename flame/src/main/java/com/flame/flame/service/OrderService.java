package com.flame.flame.service;

import com.flame.flame.model.OrderModel;
import com.flame.flame.model.ProductModel;
import com.flame.flame.model.UserModel;
import com.flame.flame.repository.OrderRepository;
import com.flame.flame.repository.ProductRepository;
import com.flame.flame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class OrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    public void createOrder(OrderModel orderModel, Principal principal) {
        UserModel user = userRepository.findByUsername(principal.getName());
        ProductModel productModel = productRepository.findById(orderModel.getProductId()).get();
        OrderModel order = new OrderModel();

        order.setUserId(user.getId());
        order.setProductId(orderModel.getProductId());
        order.setQuantity(orderModel.getQuantity());
        order.setOrderDate(LocalDateTime.now());
        order.setAddress(orderModel.getAddress());
        order.setStatus("new");
        order.setTotalPrice(orderModel.getTotalPrice());
        order.setDeliveryFee(orderModel.getDeliveryFee());
        order.setGovernorateName(orderModel.getGovernorateName());
        orderRepository.save(order);
    }
}
