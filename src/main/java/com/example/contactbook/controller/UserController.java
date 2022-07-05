package com.example.contactbook.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.contactbook.entities.Contact;
import com.example.contactbook.repo.ContactRepo;
import com.example.contactbook.repo.UserRepo;

@Controller
@RequestMapping("/user")
public class UserController implements WebMvcConfigurer {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ContactRepo contactrRepo;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/dashboard/dashboard").setViewName("dashboard/dashboard");
    }

    @ModelAttribute
    public void defaultRun(Model model, Principal principal) {
        var userDetails = userRepo.getUserByUserName(principal.getName());
        model.addAttribute("user", userDetails);

    }

    @GetMapping("/index")
    public String getIndex() {
        return "dashboard/main";
    }

    @GetMapping("/dashboard")
    public String dashBoard(Model model) {
        model.addAttribute("contact", new Contact());
        return "dashboard/dashboard";
    }

    @PostMapping("/addcontact")
    public String saveContact(Model model, @ModelAttribute("contact") Contact contact, BindingResult bindingResult,
            @RequestParam(value = "checkbox", defaultValue = "false") boolean checkbox,
            HttpSession session,Principal principal) {

        System.out.println(contact);
        contact.setUser(userRepo.getUserByUserName(principal.getName()));
        contactrRepo.save(contact);

        return "dashboard/dashboard";
    }

}
