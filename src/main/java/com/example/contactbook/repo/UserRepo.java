package com.example.contactbook.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.contactbook.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("from User u where u.email=?1")
    public User getUserByUserName(@Param("email") String email);

}
