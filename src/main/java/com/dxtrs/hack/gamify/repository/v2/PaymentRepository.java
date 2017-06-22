package com.dxtrs.hack.gamify.repository.v2;

import com.dxtrs.hack.gamify.model.User;
import com.dxtrs.hack.gamify.model.v2.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Long> {
    List<Payment> findByUserOrderByCreatedTSDesc(User user);

    List<Payment> findByCategoryOrderByCreatedTSDesc(String category);
}
