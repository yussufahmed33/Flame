package com.flame.flame.controller;

import com.flame.flame.model.UserModel;
import com.flame.flame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SystemController {
    @Autowired
    UserService userService;
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
}