package com.dxtrs.hack.gamify.controller;

import com.dxtrs.hack.gamify.dto.ChartData;
import com.dxtrs.hack.gamify.dto.OrderRequestDTO;
import com.dxtrs.hack.gamify.model.Order;
import com.dxtrs.hack.gamify.model.Product;
import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.repository.ChartDataRepository;
import com.dxtrs.hack.gamify.repository.OrderRepository;
import com.dxtrs.hack.gamify.repository.ProductRepository;
import com.dxtrs.hack.gamify.repository.UserRepository;
import com.dxtrs.hack.gamify.util.GamifierUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrdersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ChartDataRepository chartDataRepository;

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
        if (orderRequest.getPromotionCode() != null) {
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
    @RequestMapping(value = "/api/orders/users/count", method = RequestMethod.GET)
    public List<ChartData> getOrdersByUsersCount() {
        return chartDataRepository.countOfOrdersByUsers();
    }

    @RequestMapping(value = "/api/orders/categories/count", method = RequestMethod.GET)
    public List<ChartData> getOrdersByCategoryCount() {
        return chartDataRepository.countOfOrdersByCategory();
    }

}
