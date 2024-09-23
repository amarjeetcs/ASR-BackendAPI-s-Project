/*
 * package com.asr.website.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import com.asr.website.model.JobApplication; import
 * com.asr.website.service.JobApplicationService;
 * 
 * @RestController
 * 
 * @CrossOrigin(origins = "http://127.0.0.1:5500")
 * 
 * @RequestMapping("/api/job-applications") public class
 * JobApplicationController {
 * 
 * @Autowired private JobApplicationService jobApplicationService;
 * 
 * @PostMapping public ResponseEntity<JobApplication>
 * submitApplication(@RequestBody JobApplication application) { JobApplication
 * savedApplication = jobApplicationService.submitApplication(application);
 * return ResponseEntity.ok(savedApplication); } }
 */

package com.asr.website.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.asr.website.model.JobApplication;
import com.asr.website.service.JobApplicationService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping
    public ResponseEntity<JobApplication> submitApplication(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("expertise") String expertise,
            @RequestParam("experience") int experience,
            @RequestParam("bio") String bio,
            @RequestParam("resume") MultipartFile resume) {
        
        JobApplication application = new JobApplication();
        application.setName(name);
        application.setEmail(email);
        application.setPhone(phone);
        application.setExpertise(expertise);
        application.setExperience(experience);
        application.setBio(bio);

        try {
            String resumePath = jobApplicationService.saveResume(resume);
            application.setResumePath(resumePath);
            application.setResumeData(resume.getBytes()); // Optional
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

        JobApplication savedApplication = jobApplicationService.submitApplication(application);
        return ResponseEntity.ok(savedApplication);
    }
}
