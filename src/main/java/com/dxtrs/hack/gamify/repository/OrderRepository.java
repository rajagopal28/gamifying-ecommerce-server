package com.dxtrs.hack.gamify.repository;

import com.dxtrs.hack.gamify.model.Order;
import com.dxtrs.hack.gamify.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    Order findByQuantity(String firstName);

    List<Order> findByPromotion(String lastName);

    List<Order> findByUser(User user);

    @Query(value = "SELECT COUNT(o.id) FROM orders o WHERE o.user_id = :userId", nativeQuery = true)
    Long countOfUserOrders(@Param("userId") long userId);

    @Query(value = "SELECT COUNT(o.id), o.user_id FROM orders o group by o.user_id", nativeQuery = true)
    Long countOfOrders();

}
