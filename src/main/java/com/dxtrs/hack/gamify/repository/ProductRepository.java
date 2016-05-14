package com.dxtrs.hack.gamify.repository;

import com.dxtrs.hack.gamify.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByName(String firstName);

    List<Product> findByCategory(String category);

    List<Product> findBySubCategory(String subCategory);

    @Query(value = "SELECT DISTINCT(p.category) FROM products p", nativeQuery = true)
    List<String> getAllCategories();

    @Query(value = "SELECT DISTINCT(p.sub_category) FROM products p where p.category=:categoryId", nativeQuery = true)
    List<String> getAllSubCategories(@Param("categoryId") String categoryId);
}
