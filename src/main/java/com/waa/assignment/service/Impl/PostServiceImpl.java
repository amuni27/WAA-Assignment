package com.waa.assignment.service.Impl;

import com.waa.assignment.domain.Post;
import com.waa.assignment.repository.PostRepository;
import com.waa.assignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post getById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }

    public List<Post> findAllPostMatchTitle(String title) {
        return postRepository.findAllPostMatchTitle(title);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }


}
