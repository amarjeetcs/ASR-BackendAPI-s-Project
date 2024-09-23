/*
 * package com.asr.website.service.impl;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.asr.website.model.Trainer; import
 * com.asr.website.repository.TrainerRepository; import
 * com.asr.website.service.TrainerService;
 * 
 * @Service public class TrainerServiceImpl implements TrainerService {
 * 
 * @Autowired private TrainerRepository trainerRepository;
 * 
 * @Override public Trainer submitApplication(Trainer trainer) { return
 * trainerRepository.save(trainer); } }
 */


package com.asr.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.asr.website.model.Trainer;
import com.asr.website.repository.TrainerRepository;
import com.asr.website.service.TrainerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    private static final String UPLOAD_DIR = "C:/Users/Pc/Desktop/ASR-WEB-DOCS/";

    @Override
    public Trainer submitApplication(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Override
    public Trainer submitApplication(Trainer trainer, MultipartFile resume) {
        // Save the uploaded resume file to the file system
        String resumeFilePath = saveResumeFile(resume);
        trainer.setResumePath(resumeFilePath);
        
        // Optionally, you could also save the resume as binary data
        try {
            trainer.setResumeData(resume.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read file data", e);
        }

        // Save the trainer object in the database
        return trainerRepository.save(trainer);
    }

    private String saveResumeFile(MultipartFile resume) {
        if (resume.isEmpty()) {
            return null; // Handle accordingly if no file is uploaded
        }

        String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        try {
            Files.createDirectories(filePath.getParent()); // Ensure the directory exists
            Files.write(filePath, resume.getBytes());
            return filePath.toString(); // Return the file path for storage in the database
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to store file " + fileName, e);
        }
    }
}
