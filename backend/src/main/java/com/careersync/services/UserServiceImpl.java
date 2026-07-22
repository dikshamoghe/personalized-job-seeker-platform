package com.careersync.services;

import com.careersync.dto.LoginRequest;
import com.careersync.dto.LoginResponse;
import com.careersync.entities.User;
import com.careersync.exception.UserNotFoundException;
import com.careersync.repository.UserRepo;
import com.careersync.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {

        // Check if email already exists
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user
        return userRepo.save(user);
    }


    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {

        User user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isPasswordMatch = passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        );

        if (isPasswordMatch) {
            String token = jwtService.generateToken(user.getEmail());

            return new LoginResponse(
                    "Login Successful",
                    token
            );
        } else {
            throw new RuntimeException("Invalid Password");
        }
    }

    @Override
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
