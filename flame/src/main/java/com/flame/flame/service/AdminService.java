package com.flame.flame.service;

import com.flame.flame.model.OrderModel;
import com.flame.flame.model.ProductImage;
import com.flame.flame.model.ProductModel;
import com.flame.flame.model.UserModel;
import com.flame.flame.repository.OrderRepository;
import com.flame.flame.repository.ProductImageRepository;
import com.flame.flame.repository.ProductRepository;
import com.flame.flame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    OrderRepository orderRepository;
    public void addproduct(ProductModel productModel, MultipartFile[] images, Principal principal) {
        try {
            // ربط المنتج بالمستخدم الحالي
            String username = principal.getName();
            UserModel user = userRepository.findByUsername(username);
            productModel.setUser(user);
            productModel.setQuantity(0);
            // حفظ المنتج أولاً للحصول على الـ ID
            productRepository.save(productModel);

            // حفظ الصور
            if (images != null && images.length > 0) {
                String uploadDir = "uploads/";

                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) uploadFolder.mkdirs();

                boolean isFirstImage = true; // علشان نعرف الصورة الرئيسية

                for (MultipartFile imageFile : images) {
                    if (imageFile != null && !imageFile.isEmpty()) {
                        String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                        Path filePath = Paths.get(uploadDir, fileName);
                        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                        // لو دي أول صورة، نخليها الصورة الرئيسية
                        if (isFirstImage) {
                            productModel.setImage(uploadDir + fileName);
                            productRepository.save(productModel);
                            isFirstImage = false;
                        }

                        // إنشاء كائن صورة وربطه بالمنتج
                        ProductImage imageModel = new ProductImage();
                        imageModel.setImagePath(uploadDir + fileName);
                        imageModel.setProduct(productModel);

                        productImageRepository.save(imageModel);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("فشل في حفظ الصور: " + e.getMessage(), e);
        }
    }


    public List<OrderModel> getAllOrders(){
        return orderRepository.findAll();
    }
    public List<ProductModel> getAllProducts(){
        return productRepository.findAll();
    }
}
