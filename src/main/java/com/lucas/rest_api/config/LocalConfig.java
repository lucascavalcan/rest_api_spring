package com.lucas.rest_api.config;

import com.lucas.rest_api.domain.User;
import com.lucas.rest_api.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

//tests profile configuration
@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @PostConstruct
    public void startDB() {
        User u1 = new User(null, "Fulano", "fulany2y@gmail.com", "123");
        User u2 = new User(null, "Ciclano", "ciclany3y@gmail.com", "123");

        repository.saveAll(List.of(u1, u2));
    }
}
