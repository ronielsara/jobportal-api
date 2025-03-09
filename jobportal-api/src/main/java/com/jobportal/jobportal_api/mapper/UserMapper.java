package com.jobportal.jobportal_api.mapper;

import com.jobportal.jobportal_api.model.dto.request.UserRegistrationRequest;
import com.jobportal.jobportal_api.model.dto.response.UserResponse;
import com.jobportal.jobportal_api.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResource(User user) {
        return new UserResponse(user);
    }

    public User toEntity(UserRegistrationRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        return user;
    }
}
