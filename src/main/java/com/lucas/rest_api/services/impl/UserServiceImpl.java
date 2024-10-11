package com.lucas.rest_api.services.impl;

import com.lucas.rest_api.domain.User;
import com.lucas.rest_api.repositories.UserRepository;
import com.lucas.rest_api.services.UserService;
import com.lucas.rest_api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    public List<User> findAll(){
        return repository.findAll();
    }
}
