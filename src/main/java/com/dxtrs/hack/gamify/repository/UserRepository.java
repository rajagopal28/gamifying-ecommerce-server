package com.dxtrs.hack.gamify.repository;

import com.dxtrs.hack.gamify.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    User findByFirstName(String firstName);

    List<User> findByLastName(String lastName);
}
