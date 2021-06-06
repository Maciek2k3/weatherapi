package com.weather.api.repo;

import com.weather.api.domian.dto.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends CrudRepository<AppUser, Long> {


    @Override
    <S extends AppUser> S save(S s);

    AppUser findApiUserByUserName(String user);

}
