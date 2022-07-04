package com.example.contactbook.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.contactbook.entities.User;
import com.example.contactbook.helper.Message;
import com.example.contactbook.repo.ContactRepo;
import com.example.contactbook.repo.UserRepo;

@Controller
// @RequestMapping("/user")
public class Maincontroller implements WebMvcConfigurer {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/signin").setViewName("signin");
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home- Smart Contact Manager");
        // model.addAttribute("user", new User());
        /*
         * User user = new User();
         * Contact c = new Contact();
         * user.setId(1);
         * user.setName("pulu");
         * user.setPassword("qwertyytrewq");
         * user.setEmail("pulu@gmail.com");
         * user.setRole("student");
         * user.setImageUrl("www.google.com");
         * user.setEnabled(true);
         * user.setAbout("lorem36");
         * 
         * userRepo.save(user);
         * 
         * c.setName("pulu");
         * c.setDescription("description");
         * c.setEmail("email");
         * c.setImage("image");
         * c.setPhone("phone");
         * c.setSecondName("secondName");
         * c.setWork("work");
         * c.setUser(user);
         * contactRepo.save(c);
         * 
         */
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "about- Smart Contact Manager");
        return "about";
    }

    @GetMapping("/signin")
    public String signIn(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("Register", "new contact");
        return "signin";
    }

    @PostMapping("/regester")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
            @RequestParam(value = "checkbox", defaultValue = "false") boolean checkbox,
            Model model, HttpSession session) {

        try {

            if (bindingResult.hasErrors()) {
                // session.setAttribute(, arg1);
                return "signin";
            }

            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            System.out.println(checkbox);
            System.out.println(user);

            var result = userRepo.save(user);
            System.out.println(result);
            session.setAttribute("message", new Message("you are successfully regestred", "alert-success"));

            return "signin";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("something went wrong" + e.getMessage(), "alert-danger"));
            return "signin";
        }

    }

    @GetMapping("/login")
    public String logIn(Model model) {
        model.addAttribute("title","login to your account");
        return "login";
    }

}