package com.flame.flame.service;

import com.flame.flame.model.ProductModel;
import com.flame.flame.model.Roles;
import com.flame.flame.model.UserModel;
import com.flame.flame.repository.ProductRepository;
import com.flame.flame.repository.RolesRepository;
import com.flame.flame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    ProductRepository productRepository;
    public void register(UserModel user,int roleId){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        Roles role = rolesRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleId));
        Set<Roles> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

    }
    public List<ProductModel> index(){

        return productRepository.findAll();
    }
}
