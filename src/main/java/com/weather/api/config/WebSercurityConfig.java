package com.weather.api.config;

import com.weather.api.repo.AppUserRepo;
import com.weather.api.service.UserServiceImplement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@NoArgsConstructor
public class WebSercurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private UserServiceImplement userServiceImplement;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImplement);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/weather")
                .hasRole("USER").and().formLogin().permitAll().and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get(){
        AppUser appUser=new AppUser(1L,"User",passwordEncoder().encode("user"),"ROLE_USER");
        AppUser appUser2=new AppUser(2L,"User1",passwordEncoder().encode("user"),"ROLE_USER");
        appUserRepo.save(appUser);
        appUserRepo.save(appUser2);
    }


}




