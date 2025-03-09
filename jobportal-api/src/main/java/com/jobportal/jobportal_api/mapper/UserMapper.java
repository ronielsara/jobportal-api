package com.jobportal.jobportal_api.mapper;

import com.jobportal.jobportal_api.model.dto.response.UserResponse;
import com.jobportal.jobportal_api.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResource(User user) {
        return new UserResponse(user);
    }
}


