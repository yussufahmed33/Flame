package com.flame.flame.service;

import com.flame.flame.model.ProductModel;
import com.flame.flame.model.UserModel;
import com.flame.flame.repository.ProductRepository;
import com.flame.flame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AdminService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
public void addproduct(ProductModel productModel, Principal principal){
    String user1 = principal.getName();
    UserModel user = userRepository.findByUsername(user1);
    productModel.setUser(user);
    productRepository.save(productModel);
}
}
