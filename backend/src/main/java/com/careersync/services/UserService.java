package com.careersync.services;

import com.careersync.dto.LoginRequest;
import com.careersync.dto.LoginResponse;
import com.careersync.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User registerUser(User user);
    LoginResponse loginUser(LoginRequest loginRequest);
    User getUserByEmail(String email);
}
