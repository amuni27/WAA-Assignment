package com.waa.assignment.service.Impl;

import com.waa.assignment.annotation.ExecutionTime;
import com.waa.assignment.domain.Post;
import com.waa.assignment.domain.User;
import com.waa.assignment.repository.UserRepository;
import com.waa.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @ExecutionTime
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<Post> getPostByUserId(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(User::getPosts).orElse(null);
    }

    public List<User> findUsersWithMoreThanNPosts(Integer postCount) {
        return userRepository.findUsersWithMoreThanNPosts(postCount);
    }
}
