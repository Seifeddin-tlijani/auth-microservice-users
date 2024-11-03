package com.abidi.users_microservices.repos;

import com.abidi.users_microservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {
    User findByUsername(String username);
}
