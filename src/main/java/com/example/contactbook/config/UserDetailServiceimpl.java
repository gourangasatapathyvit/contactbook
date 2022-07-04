package com.example.contactbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.contactbook.entities.User;
import com.example.contactbook.repo.UserRepo;

public class UserDetailServiceimpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getUserByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("couldnot found the user: " + username);
        }

        var customUserDetails = new CustomUserDetails(user);

        return customUserDetails;
    }

}
