package com.dxtrs.hack.gamify.controller;

import com.dxtrs.hack.gamify.dto.OrderRequestDTO;
import com.dxtrs.hack.gamify.model.Order;
import com.dxtrs.hack.gamify.model.Product;
import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.repository.OrderRepository;
import com.dxtrs.hack.gamify.repository.ProductRepository;
import com.dxtrs.hack.gamify.repository.UserRepository;
import com.dxtrs.hack.gamify.util.GamifierUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private OrderRepository orderRepository;


    @RequestMapping("/api/all-categories")
    public List<String> getCategoryList() {
        return productRepository.getAllCategories();
    }

    @RequestMapping("/api/all-sub-categories")
    public List<String> getSubCategoryList(@RequestParam(value = "category", required = true) String categoryId) {
        return productRepository.getAllSubCategories(categoryId);
    }
    @RequestMapping("/api/products/all")
    public Iterable<Product> getProductsList() {
        return productRepository.findAll();
    }


    @RequestMapping(value="/api/products/add", method= RequestMethod.POST)
    public @ResponseBody
    Product addNewUser(@ModelAttribute() Product product) throws ParseException {
        product.setCreatedTS(GamifierUtil.getCurrentDate());
        product.setLastUpdatedTS(GamifierUtil.getCurrentDate());
        return productRepository.save(product);
    }


    @RequestMapping(value = "/api/orders/add", method = RequestMethod.POST)
    public Order orderProducts(@ModelAttribute() OrderRequestDTO orderRequest) {
        Order newOrder = new Order();
        Product product = productRepository.findOne(orderRequest.getProductId());
        User buyingUser = userRepository.findOne(orderRequest.getUserId());
        newOrder.setProduct(product);
        newOrder.setUser(buyingUser);
        newOrder.setQuantity(orderRequest.getQuantity());
        newOrder.setCreatedTS(GamifierUtil.getCurrentDate());
        newOrder.setLastUpdatedTS(GamifierUtil.getCurrentDate());
        if(orderRequest.getPromotionCode() != null) {
            // do add promotions from promotions repository
        }
        return orderRepository.save(newOrder);
    }


    @RequestMapping(value = "/api/orders/all", method = RequestMethod.GET)
    public Iterable<Order> getOrdersByCustomer(@RequestParam(value = "userId", required = true) Long userId) {
        User user = new User();
        user.setId(userId);
        System.out.println(orderRepository.countOfUserOrders(userId));
        return orderRepository.findByUser(user);
    }


}
