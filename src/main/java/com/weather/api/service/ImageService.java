package com.weather.api.service;

import com.weather.api.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ImageService {

    @Autowired
    ImageRepo imageRepo;

    public String getPictureAdress(Long bookId) {
        return imageRepo.findAdressByBookId(bookId);
    }
}
