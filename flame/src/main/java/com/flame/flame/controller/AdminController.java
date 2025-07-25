package com.flame.flame.controller;

import com.flame.flame.model.ProductModel;
import com.flame.flame.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    @PostMapping("/addproduct")
    public void addproduct(@ModelAttribute ProductModel productModel, Principal principal){
        adminService.addproduct(productModel,principal);
    }
}
