package com.example.login.dto.response;

import com.example.login.entity.User;

public record UserRegisterResponse(
        String username,
        String email
) {
    public static UserRegisterResponse fromEntity(User user){
        return new UserRegisterResponse(
                user.getUsername(),
                user.getEmail()
        );
    }
}

