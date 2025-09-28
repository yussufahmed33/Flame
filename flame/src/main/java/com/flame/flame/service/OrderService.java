package com.flame.flame.service;

import com.flame.flame.model.OrderModel;
import com.flame.flame.model.ProductModel;
import com.flame.flame.model.UserModel;
import com.flame.flame.repository.OrderRepository;
import com.flame.flame.repository.ProductRepository;
import com.flame.flame.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    public void createOrder(OrderModel orderModel, Principal principal, HttpSession session, Model model) {
        System.out.println("order in service");
        UserModel user = (principal != null) ? userRepository.findByUsername(principal.getName()) : null;
        ProductModel product = productRepository.findById(orderModel.getProductId()).orElseThrow();
        double subtotal = product.getPrice() * orderModel.getQuantity();
        double total = subtotal + orderModel.getDeliveryFee();
        double deliveryFee = orderModel.getDeliveryFee();

        orderModel.setUser(user);
        orderModel.setOrderDate(LocalDateTime.now());
        orderModel.setTotalPrice(total);
        orderModel.setDeliveryFee(deliveryFee);
        orderModel.setPhone(orderModel.getPhone());
        orderModel.setCity(orderModel.getCity());
        orderModel.setPostalCode(orderModel.getPostalCode());
        orderModel.setPhone(orderModel.getPhone());
        orderModel.setPaymentMethod(orderModel.getPaymentMethod());
        orderRepository.save(orderModel);


        List<OrderModel> orders = (List<OrderModel>) session.getAttribute("orders");
        if (orders == null) {
            orders = new ArrayList<>();
        }

        // Add the new order to the list
        orders.add(orderModel);

        // Save back to session
        session.setAttribute("orders", orders);

        // Optionally add the list to the model for immediate display
        model.addAttribute("orders", orders);

    }

}
