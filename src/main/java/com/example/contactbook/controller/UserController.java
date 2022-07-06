package com.example.contactbook.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    public String saveContact(Model model, @ModelAttribute("contact") Contact contact,
            @RequestParam(value = "pic") MultipartFile file, Principal principal) {

        try {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyHHmmss"));
            var fileName = date + "_" + file.getOriginalFilename();
            contact.setImage(fileName);
            System.out.println(fileName);

            // find the target folder in which you want to save
            final String filePath = new ClassPathResource("static/img").getFile().getAbsolutePath();
            var path = Paths.get(filePath + File.separator + fileName);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("image is uploaded");

            System.out.println(filePath);
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }

        System.out.println(contact);
        contact.setUser(userRepo.getUserByUserName(principal.getName()));
        contactrRepo.save(contact);

        return "dashboard/dashboard";
    }

}
