package com.niit.jap.service;

import com.niit.jap.domain.Product;
import com.niit.jap.domain.User;
import com.niit.jap.exception.ProductNotFoundException;
import com.niit.jap.exception.UserNotFoundException;
import com.niit.jap.proxy.UserProxy;
import com.niit.jap.repository.UserProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserProductServiceImpl implements UserProductService{
    @Autowired
    public UserProductRepository userProductRepository;
    @Autowired
    public UserProxy userProxy;

    @Override
    public User addUser(User user) throws UserNotFoundException {
        if (userProductRepository.findById(user.getUserId()).isPresent()){
            throw new UserNotFoundException();
        }
        User savedUser = userProductRepository.save(user);
        if (!(savedUser.getEmailId().isEmpty())){
            ResponseEntity responseEntity = userProxy.saveUser(user);
            System.out.println(responseEntity.getBody());
        }
        return savedUser;
    }

    @Override
    public User addProductForUser(int userId, Product product) throws UserNotFoundException {
        if (userProductRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userProductRepository.findByUserId(userId);
        if (user.getProductList()==null){
            user.setProductList(Arrays.asList(product));
        }
        else {
            List<Product>products=user.getProductList();
            products.add(product);
            user.setProductList(products);
        }
        return userProductRepository.save(user);
    }

    @Override
    public User deleteProductForUser(int userId, int productId) throws UserNotFoundException {
        boolean productIdIsPresent = false;
        if(userProductRepository.findById(userId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userProductRepository.findById(userId).get();
        List<Product> products = user.getProductList();
        productIdIsPresent = products.removeIf(x->x.equals(productId));
        if(!productIdIsPresent)
        {
            throw new UserNotFoundException();
        }
        user.setProductList(products);
        return userProductRepository.save(user);
    }

}
