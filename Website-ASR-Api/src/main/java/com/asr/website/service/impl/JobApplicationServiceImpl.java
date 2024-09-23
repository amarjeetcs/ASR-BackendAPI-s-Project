/*
 * package com.asr.website.service.impl;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.asr.website.model.JobApplication; import
 * com.asr.website.repository.JobApplicationRepository; import
 * com.asr.website.service.JobApplicationService;
 * 
 * @Service public class JobApplicationServiceImpl implements
 * JobApplicationService {
 * 
 * @Autowired private JobApplicationRepository jobApplicationRepository;
 * 
 * @Override public JobApplication submitApplication(JobApplication application)
 * { return jobApplicationRepository.save(application); } }
 */

package com.asr.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.asr.website.model.JobApplication;
import com.asr.website.repository.JobApplicationRepository;
import com.asr.website.service.JobApplicationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	private static final String UPLOAD_DIR = "C:/Users/Pc/Desktop/ASR-WEB-DOCS/";

	@Override
	public JobApplication submitApplication(JobApplication application) {
		return jobApplicationRepository.save(application);
	}

	@Override
	public String saveResume(MultipartFile resume) throws IOException {
		if (resume.isEmpty()) {
			return null;
		}

		String fileName = System.currentTimeMillis() + "_" + resume.getOriginalFilename();
		Path filePath = Paths.get(UPLOAD_DIR + fileName);
		Files.createDirectories(filePath.getParent());
		Files.write(filePath, resume.getBytes());
		return filePath.toString(); // Return the path of the saved resume
	}
}
