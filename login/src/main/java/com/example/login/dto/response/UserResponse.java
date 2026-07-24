package com.example.login.dto.response;

import com.example.login.entity.User;

public record UserResponse(
        String username,
        String email
) {
    public static UserResponse fromEntity(User user){
        return new UserResponse(
                user.getUsername(),
                user.getEmail()
        );
    }
}
