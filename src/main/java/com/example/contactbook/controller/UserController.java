package com.example.contactbook.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.contactbook.repo.UserRepo;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/index")
    public String getIndex(Model model,Principal principal) {
        var userDetails = userRepo.getUserByUserName(principal.getName());
        model.addAttribute("user", userDetails);
        // System.out.println(userDetails);
        return "dashboard";
    }

}
