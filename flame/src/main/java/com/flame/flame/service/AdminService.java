package com.flame.flame.service;

import com.flame.flame.model.ProductModel;
import com.flame.flame.model.UserModel;
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
import java.util.UUID;

@Service
public class AdminService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    public void addproduct(ProductModel productModel, MultipartFile imageFile, Principal principal) {
        try {
            // ربط المنتج بالمستخدم الحالي
            String username = principal.getName();
            UserModel user = userRepository.findByUsername(username);
            productModel.setUser(user);

            // حفظ الصورة إن وُجدت
            if (imageFile != null && !imageFile.isEmpty()) {
                String uploadDir = "uploads/";
                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) uploadFolder.mkdirs();

                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                productModel.setImage(String.valueOf(filePath)); // تخزين اسم الصورة فقط
            }

            // حفظ المنتج
            productRepository.save(productModel);

        } catch (IOException e) {
            throw new RuntimeException("فشل في حفظ الصورة: " + e.getMessage(), e);
        }
    }

}
