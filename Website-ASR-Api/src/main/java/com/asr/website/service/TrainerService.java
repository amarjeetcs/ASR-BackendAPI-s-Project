package com.asr.website.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.asr.website.model.Trainer;

@Service
public interface TrainerService {
	Trainer submitApplication(Trainer trainer);
	Trainer submitApplication(Trainer trainer, MultipartFile resume);
}
