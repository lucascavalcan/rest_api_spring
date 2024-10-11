package com.lucas.rest_api.services;

import com.lucas.rest_api.domain.User;

public interface UserService {

    User findById(Integer id);
}
