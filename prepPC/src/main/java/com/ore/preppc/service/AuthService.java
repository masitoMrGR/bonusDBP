package com.ore.preppc.service;

import com.ore.preppc.dto.AuthToken;
import com.ore.preppc.dto.LoginDTO;
import com.ore.preppc.entity.User;
import com.ore.preppc.repository.UserRepository;
import com.ore.preppc.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthToken login(LoginDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow();

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException();
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthToken(token);
    }
}