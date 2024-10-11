package com.lucas.rest_api.services;

import com.lucas.rest_api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
}
