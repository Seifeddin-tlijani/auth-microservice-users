package com.abidi.users_microservices.repos;

import com.abidi.users_microservices.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role , Long> {
    Role findByRole(String role);
}
