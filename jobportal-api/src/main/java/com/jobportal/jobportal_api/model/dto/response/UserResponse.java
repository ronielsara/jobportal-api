package com.jobportal.jobportal_api.model.dto.response;

import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.model.enums.UserRole;

public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private UserRole role;


    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}
