package com.asr.website.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asr.website.model.Contact;
import com.asr.website.repository.ContactRepository;
import com.asr.website.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact saveContact(Contact contact) {
		return contactRepository.save(contact);
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Optional<Contact> getContactById(Long id) {
		return contactRepository.findById(id);
	}

	@Override
	public Contact updateContact(Long id, Contact updatedContact) {
		return contactRepository.findById(id).map(contact -> {
			contact.setFirstName(updatedContact.getFirstName());
			contact.setLastName(updatedContact.getLastName());
			contact.setEmail(updatedContact.getEmail());
			contact.setMessage(updatedContact.getMessage());
			contact.setAdditionalDetails(updatedContact.getAdditionalDetails());
			return contactRepository.save(contact);
		}).orElseGet(() -> {
			updatedContact.setId(id);
			return contactRepository.save(updatedContact);
		});
	}

	@Override
	public void deleteContact(Long id) {
		contactRepository.deleteById(id);
	}
}
