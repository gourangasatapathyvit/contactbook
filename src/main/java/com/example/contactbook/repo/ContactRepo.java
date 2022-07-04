package com.example.contactbook.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contactbook.entities.Contact;

public interface ContactRepo extends JpaRepository<Contact, Integer> {
    

    
}
