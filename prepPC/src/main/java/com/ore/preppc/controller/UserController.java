package com.ore.preppc.controller;
import org.springframework.http.HttpStatus;
import com.ore.preppc.dto.NewIdDTO;
import com.ore.preppc.dto.RegisterUserDTO;
import com.ore.preppc.entity.User;
import com.ore.preppc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<NewIdDTO> register(@RequestBody RegisterUserDTO dto) {

        User user = userService.register(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new NewIdDTO(user.getId())
                );
    }
}