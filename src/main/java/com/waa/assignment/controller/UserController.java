package com.waa.assignment.controller;

import com.waa.assignment.domain.Post;
import com.waa.assignment.domain.User;
import com.waa.assignment.repository.UserRepository;
import com.waa.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        System.out.println(user);
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>( userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id) {
         return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<?> getUserPostByID(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getPostByUserId(id),HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getUserByPostCount(@RequestParam("count") Integer count) {
        return new ResponseEntity<>(userService.findUsersWithMoreThanNPosts(count),HttpStatus.OK);
    }


}
