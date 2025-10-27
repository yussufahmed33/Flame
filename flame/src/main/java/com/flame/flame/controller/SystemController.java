package com.flame.flame.controller;

import com.flame.flame.dto.CartItem;
import com.flame.flame.model.GovernorateModel;
import com.flame.flame.model.OrderModel;
import com.flame.flame.model.ProductModel;
import com.flame.flame.model.UserModel;
import com.flame.flame.repository.GovernorateRepository;
import com.flame.flame.repository.ProductRepository;
import com.flame.flame.repository.UserRepository;
import com.flame.flame.service.CartService;
import com.flame.flame.service.GovernorateService;
import com.flame.flame.service.OrderService;
import com.flame.flame.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private CartService cartService;
    @Autowired
    private CartController cartController;
    @Autowired
    private GovernorateRepository governorateRepository;

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
    public String getProductDetails(@PathVariable int id, Model model,HttpSession session) {
        ProductModel product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        return "product-details";
    }
//    @GetMapping("checkout/{id}")
//    public String checkOut(@PathVariable int id, Model model, Principal principal,HttpSession session) {
//        ProductModel product = productRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//        //model.addAttribute("cart",cartService.getCart(session));
//        model.addAttribute("product", product);
//        model.addAttribute("quantity", 1);
//        model.addAttribute("totalPrice", product.getPrice());
//
//
////        System.out.println(cartService.getCart(session));
////        System.out.println(product);
//        if (principal != null) {
//            UserModel user = userRepository.findByUsername(principal.getName());
//           // System.out.println(user);
//
//            // إنشاء نسخة آمنة بدون بيانات حساسة
//            UserModel safeUser = new UserModel();
//            safeUser.setFirstName(user.getFirstName());
//            safeUser.setLastName(user.getLastName());
//            safeUser.setEmail(user.getEmail());
//            model.addAttribute("governorates",governorateService.getAllGovernorates());
//            model.addAttribute("auser", user);
//            model.addAttribute("fullName", user.getFirstName() + " " + user.getLastName());
//        }
//
//        return "checkout";
//    }



    @PostMapping("/confirm-order")
    public String createOrder(@ModelAttribute OrderModel orderModel,Principal principal,HttpSession session,Model model) {
       orderService.createOrder(orderModel,principal,session,model);
       return "redirect:/";
    }
//@GetMapping("/product/{id}")
//    public String productDetails(@PathVariable int id,Model model){
//        model.addAttribute("product",productRepository.findById(id));
//        return "product-details";
//}

@GetMapping("/buynow/{id}/{qty}")
    public String checkOut(@PathVariable int id ,Model model ,HttpSession session ,Principal principal,@PathVariable int qty){
        List<CartItem> cart = cartService.getCart(session);
        ProductModel product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    //model.addAttribute("product",product);
    model.addAttribute("prods",product);
    model.addAttribute("quantity",qty);
    model.addAttribute("governorate",governorateService.getAllGovernorates());
    model.addAttribute("governorates",governorateService.getAllGovernorates());


  //  int q = (int) session.getAttribute("currentQuantity");
    //model.addAttribute("currentQuantity",q);
    //System.out.println(product);
       // model.addAttribute("cart",cart);
   // model.addAttribute("cartItems",cart);
   // model.addAttribute("customer",session != null? null:principal);
        return "checkout";

}
//@PostMapping("/checkout/{id}/{qty}")
//    public String makeOrder(@RequestBody OrderModel orderModel,@PathVariable int id,@PathVariable int qty ,Principal principal){
//        orderService.createOrder(orderModel, principal);
//        return "redirect:/";
//}
@PostMapping("/checkout/process/{productId}/{quantity}")
public String processOrder(@PathVariable int productId,
                           @PathVariable int quantity,
                           @RequestParam String country,
                           @RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String address,
                           @RequestParam(required = false) String apartment,
                           @RequestParam String city,
                           @RequestParam String governorate,
                           @RequestParam(required = false) String postalCode,
                           @RequestParam String phone,
                           @RequestParam(required = false) String paymentMethod,
                           Principal principal,HttpSession session,Model model) {
        ProductModel productModel = productRepository.findById(productId).orElseThrow();
    GovernorateModel governorateModel = governorateRepository.findGovernorateModelByName(governorate);
    System.out.println("order in controller");
    String fullAddress = String.join(", ",
            firstName + " " + lastName,
            address,
            (apartment == null || apartment.isBlank()) ? city : apartment + ", " + city,
            governorate.toUpperCase(),
            country + (postalCode == null || postalCode.isBlank() ? "" : " " + postalCode),
            "Phone: " + phone
    );
    //double deliveryFee = computeDelivery(governorate);
    OrderModel order = new OrderModel();
    order.setProductId(productId);
    order.setQuantity(quantity);
    order.setAddress(fullAddress);
    order.setDeliveryFee(governorateModel.getDeliveryPrice());
    order.setGovernorateName(governorate);
    order.setStatus("new");
    order.setPostalCode(postalCode);
    order.setPhone(phone);
    order.setPaymentMethod(paymentMethod);
    order.setCity(city);
    order.setProductImg(productModel.getImage());
   // order.setProductImg(productModel.ge);
    orderService.createOrder(order, principal,session,model);
    return "redirect:/order/" + productId + "/" + quantity;
}

//    private double computeDelivery(String governorate) {
//        String g = governorate == null ? "" : governorate.toLowerCase();
//        if (g.equals("cairo") || g.equals("giza") || g.equals("new-cairo") || g.equals("sheikh-zayed") || g.equals("6th-october"))
//            return 50.0;
//        return 85.0;
//    }
    //@ResponseBody
    @GetMapping("/myorders")
    public String getsessionorder(OrderModel orderModel,HttpSession session,Model model,Principal principal){
        List<OrderModel> orders = new ArrayList<>();
//String orderImg = productRepository.getReferenceById(orders.ge)
        if (principal == null) {
            // Use orders in session (guest user)
            List<OrderModel> sessionOrders = (List<OrderModel>) session.getAttribute("orders");
            if (sessionOrders != null) {
                orders = sessionOrders;
            }
        } else {
            // For logged-in user, fetch orders from DB
            UserModel user = userRepository.findByUsername(principal.getName());
          //  orders = orderRepository.findByUser(user);
        }
        double grandtotal = orders.stream()
                .mapToDouble(OrderModel::getTotalPrice)
                .sum();
        model.addAttribute("orders", orders);

     //   model.addAttribute("order",orders);
        model.addAttribute("grandTotal", grandtotal);
        System.out.println(orders);
        return "myorders";
    }

    @Transactional
    @GetMapping("/checkout")
    public String checkOut(Model model ,HttpSession session ,Principal principal){
        List<CartItem> cart = cartService.getCart(session);
//        System.out.println(cart);
        for(CartItem cartItem : cart){
            System.out.println("|||||||");
            System.out.println(cartItem);
        }
       model.addAttribute("cart",cart);
        model.addAttribute("governorate",governorateService.getAllGovernorates());
        model.addAttribute("governorates",governorateService.getAllGovernorates());
    return "cartCheckOut";
    }

    }
