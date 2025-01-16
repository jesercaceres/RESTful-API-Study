package com.study.authentication.service;

import com.study.authentication.dtos.UserDTO;

public interface UserService {
 
    UserDTO registerUser(UserDTO user);
}
