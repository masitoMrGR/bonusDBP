package com.ore.preppc.service;

import com.ore.preppc.dto.RegisterUserDTO;
import com.ore.preppc.entity.User;
import com.ore.preppc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register(RegisterUserDTO dto) {

        if (!dto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Invalid email");
        }
        if (!dto.getPassword().matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$")) {
            throw new RuntimeException("Invalid password");
        }
        if (!dto.getFirstName().matches("^[A-Z][a-zA-Z]*$")) {
            throw new RuntimeException("Invalid first name");
        }
        if (!dto.getLastName().matches("^[A-Z][a-zA-Z]*$")) {
            throw new RuntimeException("Invalid last name");
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }


        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

        return userRepository.save(user);
    }
}