package com.rajendar.springboot.contactsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajendar.springboot.contactsapp.model.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
