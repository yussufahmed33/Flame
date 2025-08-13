package com.flame.flame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
@GetMapping("/createorder")
public void createOrder() {}
}
