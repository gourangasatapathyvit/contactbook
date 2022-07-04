package com.example.contactbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.contactbook.entities.Contact;
import com.example.contactbook.entities.User;
import com.example.contactbook.repo.ContactRepo;
import com.example.contactbook.repo.UserRepo;

@SpringBootApplication
public class ContactbookApplication{

	public static void main(String[] args) {
		SpringApplication.run(ContactbookApplication.class, args);
	}

	// @Autowired
	// private UserRepo userRepo;

	// @Autowired
	// private ContactRepo contactRepo;

	// @Override
	// public void run(String... args) throws Exception {
	// 	User user = new User();
	// 	Contact c = new Contact();
	// 	user.setId(1);
	// 	user.setName("pulu");
	// 	user.setPassword("qwertyytrewq");
	// 	user.setEmail("pulu@gmail.com");
	// 	user.setRole("student");
	// 	user.setImageUrl("www.google.com");
	// 	user.setEnabled(true);
	// 	user.setAbout("lorem36");

	// 	userRepo.save(user);

	// 	c.setName("pulu");
	// 	c.setDescription("description");
	// 	c.setEmail("email");
	// 	c.setImage("image");
	// 	c.setPhone("phone");
	// 	c.setSecondName("secondName");
	// 	c.setWork("work");
	// 	c.setUser(user);
	// 	contactRepo.save(c);

	// }

}
