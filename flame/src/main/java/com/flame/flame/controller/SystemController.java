package com.flame.flame.controller;

import com.flame.flame.model.OrderModel;
import com.flame.flame.model.ProductModel;
import com.flame.flame.model.UserModel;
import com.flame.flame.repository.ProductRepository;
import com.flame.flame.repository.UserRepository;
import com.flame.flame.service.GovernorateService;
import com.flame.flame.service.OrderService;
import com.flame.flame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class SystemController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GovernorateService governorateService;
    @Autowired
    OrderService orderService;
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new UserModel());
        return "register";
    }
    @PostMapping("/register")
    public String register(UserModel userModel) {
        userService.register(userModel,1);
        return "redirect:/register";
    }
@GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("products",userService.index());
        return "index";
    }
    @GetMapping("/product/{id}")
    public String getProductDetails(@PathVariable int id, Model model) {
        ProductModel product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        return "product-details";
    }
    @GetMapping("checkout/{id}")
    public String checkOut(@PathVariable int id, Model model, Principal principal) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        model.addAttribute("product", product);
        model.addAttribute("quantity", 1);
        model.addAttribute("totalPrice", product.getPrice());

        if (principal != null) {
            UserModel user = userRepository.findByUsername(principal.getName());
           // System.out.println(user);

            // إنشاء نسخة آمنة بدون بيانات حساسة
            UserModel safeUser = new UserModel();
            safeUser.setFirstName(user.getFirstName());
            safeUser.setLastName(user.getLastName());
            safeUser.setEmail(user.getEmail());
/////////
//            List<String> governorates = Arrays.asList(
//                    "القاهرة",
//                    "الجيزة",
//                    "الشرقية",
//                    "الدقهلية",
//                    "البحيرة",
//                    "المنيا",
//                    "القليوبية",
//                    "الإسكندرية",
//                    "الغربية",
//                    "سوهاج",
//                    "أسيوط",
//                    "الفيوم",
//                    "كفر الشيخ",
//                    "قنا",
//                    "بني سويف",
//                    "المنوفية",
//                    "أسوان",
//                    "دمياط",
//                    "الإسماعيلية",
//                    "الأقصر",
//                    "بورسعيد",
//                    "السويس",
//                    "مطروح",
//                    "شمال سيناء",
//                    "البحر الأحمر",
//                    "الوادي الجديد",
//                    "جنوب سيناء"
//            );

          //  model.addAttribute("governorates", governorates);
            model.addAttribute("governorates",governorateService.getAllGovernorates());
            model.addAttribute("auser", user);
            model.addAttribute("fullName", user.getFirstName() + " " + user.getLastName());
        }

        return "checkout";
    }



    @PostMapping("/confirm-order")
    public String createOrder(@ModelAttribute OrderModel orderModel,Principal principal) {
       orderService.createOrder(orderModel,principal);
       return "redirect:/";
    }

}