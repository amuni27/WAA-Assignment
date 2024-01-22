package com.waa.assignment.controller;

import com.waa.assignment.domain.Comment;
import com.waa.assignment.domain.Post;
import com.waa.assignment.service.CommentService;
import com.waa.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @PostMapping("/{id}")
    public Comment add(@PathVariable("id") Long id, @RequestBody Comment comment) {
        Post post= postService.getById(id);
        comment.setPost(post);
        System.out.println(post);
        return commentService.save(comment);
    }
}
