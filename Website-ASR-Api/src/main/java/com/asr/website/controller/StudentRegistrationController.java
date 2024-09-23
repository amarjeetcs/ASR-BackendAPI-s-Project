package com.asr.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.asr.website.model.StudentRegistration;
import com.asr.website.service.StudentRegistrationService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/students")
public class StudentRegistrationController {

    @Autowired
    private StudentRegistrationService studentRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<StudentRegistration> registerStudent(@RequestBody StudentRegistration studentRegistration) {
        StudentRegistration savedStudent = studentRegistrationService.registerStudent(studentRegistration);
        return ResponseEntity.ok(savedStudent);
    }
}
