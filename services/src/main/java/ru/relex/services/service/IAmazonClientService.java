package ru.relex.services.service;

import org.springframework.web.multipart.MultipartFile;

public interface IAmazonClientService {
    String uploadFile(MultipartFile multipartFile);
    String deleteFileFromS3Bucket(String fileUrl);
}
