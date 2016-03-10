package com.vince.dao;

import com.vince.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username and u.password = :password")
    public User findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);
}
