package com.flame.flame.controller;

import com.flame.flame.model.ProductModel;
import com.flame.flame.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/addproduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductModel());

        return "addproduct";
    }

//    @PostMapping("/addproduct")
//    public void addproduct(@ModelAttribute ProductModel productModel,
//                           @RequestParam("images") MultipartFile[] images,
//                           Principal principal) {
//        adminService.addproduct(productModel, images, principal);
//    }
    @PostMapping("/addproduct")
    public void addproduct(
            @ModelAttribute ProductModel productModel,
            @RequestParam("productImages") MultipartFile[] images,
            Principal principal
    ) {
       adminService.addproduct(productModel,images,principal);
    }

@GetMapping("/orders")
    public String getAllOrders(Model model){
        List orders = adminService.getAllOrders();
        model.addAttribute("orders",orders);
        return"adminorders";
}
@GetMapping("/products")
    public String getAllProducts(Model model){
        List products = adminService.getAllProducts();
        model.addAttribute("products",products);
        return "allProducts";}
}
