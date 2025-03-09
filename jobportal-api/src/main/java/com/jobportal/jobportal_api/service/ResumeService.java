package com.jobportal.jobportal_api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ResumeService {

    @Value("${resume.upload-dir}")
    private String uploadDir;

    public String uploadResume(Long jobSeekerId, MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        String fileName = jobSeekerId + "-" + file.getOriginalFilename();
        File dest = new File(uploadDir + File.separator + fileName);
        dest.getParentFile().mkdirs();
        file.transferTo(dest);
        return fileName;
    }
}
