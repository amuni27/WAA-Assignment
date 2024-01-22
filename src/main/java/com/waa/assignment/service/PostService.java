package com.waa.assignment.service;

import com.waa.assignment.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

     List<Post> getAllPost() ;

     Post getById(Long id) ;

     Post save(Post post);
     List<Post> findAllPostMatchTitle(String title);
}
