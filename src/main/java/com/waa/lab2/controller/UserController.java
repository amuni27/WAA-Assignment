package com.waa.lab2.controller;

import com.waa.lab2.domain.User;
import com.waa.lab2.service.PostService;
import com.waa.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getUser(id),HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}/posts")
    public ResponseEntity<?> getUserPost(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.getPostsByUserId(id).getPosts(),HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
    }
}
