package com.rajendar.springboot.contactsapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajendar.springboot.contactsapp.model.Contact;
import com.rajendar.springboot.contactsapp.repository.ContactRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class ContactController {

	@Autowired
	private ContactRepository contactRepository;

	@GetMapping("/contacts")
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@PostMapping("/contacts")
	public Contact addContact(@RequestBody Contact contact) {
		return contactRepository.save(contact);
	}

	@PutMapping("/contacts/{id}")
	public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contactDetails) {

		Optional<Contact> c = contactRepository.findById(id);

		Contact contact = null;
		if (c.isPresent()) {
			contact = c.get();
		}

		contact.setName(contactDetails.getName());
		contact.setEmail(contactDetails.getEmail());
		contact.setPlace(contactDetails.getPlace());

		Contact updatedContact = contactRepository.save(contact);
		return ResponseEntity.ok(updatedContact);
	}

	@DeleteMapping("/contacts/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteContact(@PathVariable Long id){

		Optional<Contact> c = contactRepository.findById(id);
		
		Contact contact = null;
		if(c.isPresent()) {
			contact = c.get();
		}
		
		contactRepository.delete(contact);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted",true);
		return ResponseEntity.ok(response);
	}
}
