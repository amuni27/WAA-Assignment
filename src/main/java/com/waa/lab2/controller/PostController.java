package com.waa.lab2.controller;


import com.waa.lab2.domain.Post;
import com.waa.lab2.dto.PostDTO;
import com.waa.lab2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {


    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> save(@RequestBody Post post) {
        System.out.println(post);
        PostDTO response = postService.addPost(post);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostByID(@PathVariable("id") Long id) {
        PostDTO postDTO = postService.getPost(id);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> postList = postService.getAllPost();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }
}
