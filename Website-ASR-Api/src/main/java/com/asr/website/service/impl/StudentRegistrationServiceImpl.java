package com.asr.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asr.website.model.StudentRegistration;
import com.asr.website.repository.StudentRegistrationRepository;
import com.asr.website.service.StudentRegistrationService;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

    @Autowired
    private StudentRegistrationRepository studentRegistrationRepository;

    @Override
    public StudentRegistration registerStudent(StudentRegistration studentRegistration) {
        return studentRegistrationRepository.save(studentRegistration);
        
        
    }
}

