package com.jobportal.jobportal_api.service;

import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.model.enums.UserRole;
import com.jobportal.jobportal_api.repository.UserRepository;
import com.jobportal.jobportal_api.specification.UserSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getAllUsers(UserRole role, Pageable pageable) {
        Specification<User> spec = Specification.where(null);

        if (role != null) {
            spec = spec.and(UserSpecification.hasRole(role));
        }

        return userRepository.findAll(spec, pageable);
    }
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }
}

