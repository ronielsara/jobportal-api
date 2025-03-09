package com.jobportal.jobportal_api.controller;

import com.jobportal.jobportal_api.mapper.UserMapper;
import com.jobportal.jobportal_api.model.dto.response.UserResponse;
import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.model.enums.UserRole;
import com.jobportal.jobportal_api.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AdminUserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(required = false) UserRole role,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<User> usersPage = userService.getAllUsers(role, pageable);
        Page<UserResponse> userResponses = usersPage.map(userMapper::toResource);
        return ResponseEntity.ok(userResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
