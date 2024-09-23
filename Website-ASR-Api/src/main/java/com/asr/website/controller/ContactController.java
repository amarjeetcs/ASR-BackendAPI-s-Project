package com.asr.website.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.asr.website.model.Contact;
import com.asr.website.service.ContactService;


@RestController
@RequestMapping("/api/contacts")//localhost:8081/api/contacts
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // CREATE - Add a new contact
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact newContact = contactService.saveContact(contact);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    // READ - Get all contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // READ - Get contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contactService.getContactById(id);
        return contact.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // UPDATE - Update contact by ID
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Contact updatedContact = contactService.updateContact(id, contact);
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

    // DELETE - Delete contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

/*
 * POST /api/contacts - Add a new contact. GET /api/contacts - Retrieve all
 * contacts. GET /api/contacts/{id} - Retrieve a contact by ID. PUT
 * /api/contacts/{id} - Update a contact by ID. DELETE /api/contacts/{id} -
 * Delete a contact by ID.
 */