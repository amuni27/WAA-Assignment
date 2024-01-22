package com.waa.assignment.service.Impl;

import com.waa.assignment.domain.Comment;
import com.waa.assignment.domain.Post;
import com.waa.assignment.repository.CommentRepository;
import com.waa.assignment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
