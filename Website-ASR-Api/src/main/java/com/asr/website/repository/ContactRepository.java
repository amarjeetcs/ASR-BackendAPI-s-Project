package com.asr.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asr.website.model.Contact;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}
