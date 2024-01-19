package com.waa.lab2.service;


import com.waa.lab2.domain.Comment;
import com.waa.lab2.domain.Post;
import com.waa.lab2.domain.User;
import com.waa.lab2.dto.PostDTO;
import com.waa.lab2.repository.CommentRepository;
import com.waa.lab2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;


    public PostDTO addPost(Post post) {
        System.out.println(post);
        postRepository.save(post);
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setAuthor(post.getAuthor());
        postDTO.setContent(post.getContent());
        postDTO.setTitle(post.getTitle());
        return postDTO;
    }

    public PostDTO getPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.get().getId());
        postDTO.setAuthor(post.get().getAuthor());
        postDTO.setContent(post.get().getContent());
        postDTO.setTitle(post.get().getTitle());
        return postDTO;
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public List<Post> findPostByTitle(String post_title) {
        return postRepository.findPostsByTitle(post_title);
    }

    public Post addComment(Long post_id, Comment comment) {
        Post post = postRepository.findById(post_id).get();
        post.getComments().add(comment);
        postRepository.save(post);
        return post;
    }


}
