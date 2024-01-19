package com.waa.lab2.service;

import com.waa.lab2.domain.Post;
import com.waa.lab2.domain.User;
import com.waa.lab2.dto.UserDTO;
import com.waa.lab2.repository.PostRepository;
import com.waa.lab2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public UserDTO save(User user) {
        User response = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(response.getId());
        userDTO.setName(response.getName());
        return userDTO;
    }

    public UserDTO getUser(Long id) {
        Optional<User> response = userRepository.findById(id);
        if (response.isPresent()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(response.get().getId());
            userDTO.setName(response.get().getName());
            return userDTO;
        }
        return null;
    }

    public User getPostsByUserId(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    public List<User> findUsersWithMoreThanNPosts(Integer postCount) {
        return userRepository.findUsersWithMoreThanNPosts(postCount);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


}
