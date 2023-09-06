package com.virtual.reporting.controller.rest;

import com.virtual.reporting.dto.ApiResponseDTO;
import com.virtual.reporting.dto.response.UserDTO;
import com.virtual.reporting.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResponseDTO<List<UserDTO>> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponseDTO<UserDTO> findById(@PathVariable long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponseDTO<String> deleteById(@PathVariable long id) {
        return userService.delete(id);
    }

    @PostMapping
    public ApiResponseDTO<String> save(@RequestBody @Valid UserDTO userDTO) {
        return userService.save(userDTO);
    }
}
