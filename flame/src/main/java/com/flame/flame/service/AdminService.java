package com.flame.flame.service;

import com.flame.flame.model.ProductModel;
import com.flame.flame.model.UserModel;
import com.flame.flame.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AdminService {
    @Autowired
    ProductRepository productRepository;
public void addproduct(ProductModel productModel, Principal principal){
    // UserModel user = productModel.g();
    // productModel.setUser(user);
    // productRepository.save(productModel);
}
}
