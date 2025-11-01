package com.flame.flame.service;

import com.flame.flame.dto.CartItem;
import com.flame.flame.model.ProductModel;
import com.flame.flame.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;

    private static final String CART_SESSION_KEY = "cart";

    @Transactional
    public void addToCart(int productId, HttpSession session, int quantity) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);

        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;
        for (CartItem item : cart) {
            if (item.getProduct().getId() == productId) {   // ✅ مقارنة صحيحة
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            ProductModel product = productRepository.findById(productId).orElseThrow();
            cart.add(new CartItem(product, quantity));
        }

        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }
}
