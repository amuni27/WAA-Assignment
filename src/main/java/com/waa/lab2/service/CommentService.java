package com.waa.lab2.service;

import com.waa.lab2.domain.Comment;
import com.waa.lab2.domain.Post;
import com.waa.lab2.dto.PostDTO;
import com.waa.lab2.repository.CommentRepository;
import com.waa.lab2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public Post addComment(Long post_id, String comments) {
        Post post = postRepository.findById(post_id).get();
        Comment comment = new Comment();
        comment.setName(comments);
        post.getComments().add(comment);
        postRepository.save(post);
        return post;
    }
}
