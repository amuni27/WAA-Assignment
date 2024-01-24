package com.waa.assignment.repository;

import com.waa.assignment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u INNER JOIN u.posts p GROUP BY u HAVING COUNT(p) > :postCount")
    List<User> findUsersWithMoreThanNPosts(@Param("postCount") Integer count);

    User findByName(String name);
}
