package ru.relex.rest.controller;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.relex.rest.service.AmazonClientService;

import java.io.IOException;
import java.util.List;
//Оставила для теста, потом удалю.
@RestController
@RequestMapping("/api/aws/s3")
public class AmazonController {

    @Autowired
    private AmazonClientService amazonClientService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile multipartFiles) {
        return amazonClientService.uploadFile(multipartFiles);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam("url") String url) {
        return amazonClientService.deleteFileFromS3Bucket(url);
    }
}
