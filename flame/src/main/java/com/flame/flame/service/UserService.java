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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public UserModel getUserWithProducts(Integer userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // إجبار تحميل مجموعة المنتجات ضمن الجلسة المفتوحة
        user.getProducts().size();

        return user;
    }

    @Transactional
    public UserModel getUserWithProducts(int userId) {
        UserModel user = userRepository.findByIdWithProducts(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.getProducts().size(); // إجبار التحميل داخل الجلسة
        return user;
    }

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
