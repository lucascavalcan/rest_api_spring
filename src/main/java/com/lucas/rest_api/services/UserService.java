package com.lucas.rest_api.services;

import com.lucas.rest_api.domain.User;
import com.lucas.rest_api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
    User update(UserDTO obj);
    void delete(Integer id);
}
