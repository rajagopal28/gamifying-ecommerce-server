package com.dxtrs.hack.gamify.controller;

import com.dxtrs.hack.gamify.model.Product;
import com.dxtrs.hack.gamify.repository.ProductRepository;
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


    @RequestMapping(value = "/api/products/add", method = RequestMethod.POST)
    public
    @ResponseBody
    Product addNewUser(@ModelAttribute() Product product) throws ParseException {
        product.setCreatedTS(GamifierUtil.getCurrentDate());
        product.setLastUpdatedTS(GamifierUtil.getCurrentDate());
        return productRepository.save(product);
    }


}
