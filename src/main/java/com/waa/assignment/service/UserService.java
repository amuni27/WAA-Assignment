package com.waa.assignment.service;

import com.waa.assignment.domain.Post;
import com.waa.assignment.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     List<User> getAllUsers() ;
     User getUserById(Long id) ;
     User save(User user) ;
     public User findByName(String username);
     List<Post> getPostByUserId(Long id);
     List<User> findUsersWithMoreThanNPosts(Integer postCount);
}
