package com.flame.flame.controller;

import com.flame.flame.dto.CartItem;
import com.flame.flame.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{productId}")
    @ResponseBody
    public void addToCart(@PathVariable int productId, HttpSession session,@RequestParam(value="quantity", defaultValue="1") int quantity
    ) {
        cartService.addToCart(productId, session,quantity);
       // return "redirect:/cart/view";
       // return "redirect:/";
    }

    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cart = cartService.getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("cartItems",cart);
        double total = cart.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        model.addAttribute("grandTotal", total);

       // return "cart";
        return "crtcr";
    }

}
