package com.dxtrs.hack.gamify.controller;

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
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private OrderRepository orderRepository;


    @RequestMapping("/all-categories")
    public List<String> getCategoryList() {
        return productRepository.getAllCategories();
    }

    @RequestMapping("/all-sub-categories")
    public List<String> getSubCategoryList(@RequestParam(value = "category", required = true) String categoryId) {
        return productRepository.getAllSubCategories(categoryId);
    }
    @RequestMapping("/all-products")
    public Iterable<Product> getProductsList() {
        return productRepository.findAll();
    }


    @RequestMapping(value="/add-product", method= RequestMethod.GET)
    public @ResponseBody
    Product addNewUser(@RequestParam(value = "productName", required = true) String name,
                    @RequestParam(value = "category", required = false) String category,
                    @RequestParam(value = "subCategory", required = true) String subCategory,
                    @RequestParam(value = "quantity", required = true) Long quantity,
                    @RequestParam(value = "price", required = true) Double price,
                    @RequestParam(value = "currencyType", required = true) String currencyType) throws ParseException {
        Product product = new Product();
        product.setQuantity(quantity);
        product.setCurrencyType(currencyType);
        product.setCategory(category);
        product.setName(name);
        product.setPrice(price);
        product.setSubCategory(subCategory);
        product.setCreatedTS(GamifierUtil.getCurrentDate());
        product.setLastUpdatedTS(GamifierUtil.getCurrentDate());
        return productRepository.save(product);
    }


    @RequestMapping("/buy-product")
    public Order orderProducts(@RequestParam(value = "productId", required = true) long productId,
                                @RequestParam(value = "quantity", required = true) long quantity,
                                @RequestParam(value = "userId", required = true) long userId,
                                @RequestParam(value = "promotionCode") String promotionCode) {
        Order newOrder = new Order();
        Product product = productRepository.findOne(productId);
        User buyingUser = userRepository.findOne(userId);
        newOrder.setProduct(product);
        newOrder.setUser(buyingUser);
        newOrder.setQuantity(quantity);
        newOrder.setCreatedTS(GamifierUtil.getCurrentDate());
        newOrder.setLastUpdatedTS(GamifierUtil.getCurrentDate());
        if(promotionCode != null) {
            // do add promotions from promotions repository
        }
        return orderRepository.save(newOrder);
    }


    @RequestMapping("/all-customer-orders")
    public Iterable<Order> getOrdersByCustomer(@RequestParam(value = "userId", required = true) Long userId) {
        User user = new User();
        user.setId(userId);
        System.out.println(orderRepository.countOfUserOrders(userId));
        return orderRepository.findByUser(user);
    }


}
