package com.weather.api.repo;

import com.weather.api.domian.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ImageRepo extends CrudRepository<Image, Long> {

    @Transactional
    @Query(value = "SELECT image_adress from image where book_number=:number", nativeQuery = true)
    String findAdressByBookId(@Param("number") Long number);


}
