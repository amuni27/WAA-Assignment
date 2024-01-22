package com.waa.assignment.controller;

import com.waa.assignment.domain.Post;
import com.waa.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<?> getAllPost() {
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostByID(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getPostByID(@RequestParam("title") String title) {
        return new ResponseEntity<>(postService.findAllPostMatchTitle(title), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }
}
