package com.weather.api.domian;

import com.weather.api.repo.ImageRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class ImageTestSuite {
    @Autowired
    ImageRepo imageRepo;

    @Test
    public void testImageDaoCreateReadSave() {
        //Given
        Image image = new Image("test", 1L);
        //When
        imageRepo.save(image);
        long id = image.getId();
        Optional<Image> readImage = imageRepo.findById(id);
        //Then
        Assert.assertTrue(readImage.isPresent());

    }


    @Test
    public void testImageDaoCreateReadDelete() {
        //Given
        Image image = new Image("test", 1L);
        //When
        imageRepo.save(image);
        long id = image.getId();
        imageRepo.deleteById(id);
        Optional<Image> readImage = imageRepo.findById(id);
        //Then
        Assert.assertFalse(readImage.isPresent());

    }

    @Test
    public void testItemUpdate() {
        //Given
        Image image = new Image("test", 1L);
        //When
        imageRepo.save(image);
        image.setImageAdress("content");
        //Then
        assertEquals("content", image.getImageAdress());

    }



}
