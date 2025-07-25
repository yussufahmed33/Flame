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
<<<<<<< HEAD
    String user1 = principal.getName();
    UserModel user = userRepository.findByUsername(user1);
    productModel.setUser(user);
    productRepository.save(productModel);
=======
    // UserModel user = productModel.g();
    // productModel.setUser(user);
    // productRepository.save(productModel);
>>>>>>> 175826420a537edfee28544c22ac4fbc873be9a5
}
}
