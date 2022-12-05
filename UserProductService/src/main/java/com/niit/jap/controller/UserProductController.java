package com.niit.jap.controller;

import com.niit.jap.domain.Product;
import com.niit.jap.domain.User;
import com.niit.jap.exception.UserNotFoundException;
import com.niit.jap.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/userproductapi")
public class UserProductController {
    private UserProductService userProductService;
    private ResponseEntity<?>responseEntity;
    @Autowired
    public UserProductController(UserProductService userProductService){
        this.userProductService = userProductService;
    }
    @PostMapping("/register")
    public ResponseEntity<?>addUser(@RequestBody User user)throws UserNotFoundException{
        try {
            user.setProductList(new ArrayList<>());
            responseEntity =new ResponseEntity<>(userProductService.addUser(user), HttpStatus.OK);

        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @PutMapping("/addproduct/{userId}")
    public  ResponseEntity<?> saveUserProduct(@PathVariable int userId, @RequestBody Product product) throws UserNotFoundException{
        try {
            return new ResponseEntity<>(userProductService.addProductForUser(userId,product),HttpStatus.OK);
        }catch (Exception e){
            throw new UserNotFoundException();
        }
    }
    @DeleteMapping("/userproduct/{productId}")
    public ResponseEntity<?>deleteUserProductId(@PathVariable int productId,@RequestBody User user)throws UserNotFoundException{
        try {
            return new ResponseEntity<>(userProductService.deleteProductForUser(user.getUserId(),productId),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }

    }
}
