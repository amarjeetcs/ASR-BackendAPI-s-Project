package com.asr.website.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.asr.website.model.JobApplication;

@Service
public interface JobApplicationService {
    JobApplication submitApplication(JobApplication application);
    String saveResume(MultipartFile resume) throws IOException;
}
