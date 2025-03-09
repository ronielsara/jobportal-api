package com.jobportal.jobportal_api.specification;

import com.jobportal.jobportal_api.model.entity.User;
import com.jobportal.jobportal_api.model.enums.UserRole;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> hasRole(UserRole role) {
        return (root, query, criteriaBuilder) ->
                role == null ? null : criteriaBuilder.equal(root.get("role"), role);
    }
}
