package com.example.login.dto.request;

import com.example.login.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Email is required")
        @Email
        String email,
        @NotBlank(message = "Password is required")
        String password
) {
    public void fillIn(User user){
        user.setUsername(username);
        user.setEmail(email);
    }
}
