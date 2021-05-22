package com.weather.api.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.weather.api.domian.Image;
import com.weather.api.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploaderService {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;
    private Image image;

    @Value("${cloudNameValue}")
    private String cloudNameValue;
    @Value("${apiKeyValue}")
    private String apiKeyValue;
    @Value("${apiSecretValue}")
    private String apiSecretValue;


    @Autowired
    public ImageUploaderService(ImageRepo imageRepo,
                         @Value("${cloudNameValue}") String cloudNameValue,
                         @Value("${apiKeyValue}") String apiKeyValue,
                         @Value("${apiSecretValue}") String apiSecretValue) {
        this.imageRepo=imageRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudNameValue,
                "api_key", apiKeyValue,
                "api_secret", apiSecretValue));
    }

    public String uploadFileAndSaveToDb(String path,Long number) {
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image((uploadResult.get("url").toString()),number));
        } catch (IOException e) {
            // todo
        }
        return uploadResult.get("url").toString();
    }



}

