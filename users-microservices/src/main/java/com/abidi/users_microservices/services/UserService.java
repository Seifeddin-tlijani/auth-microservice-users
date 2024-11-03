package com.abidi.users_microservices.services;

import com.abidi.users_microservices.entities.Role;
import com.abidi.users_microservices.entities.User;

import java.util.List;

public interface UserService
{

    User saveUser(User user);
    User findUserByUsername (String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
    public List<User> findAllUsers();
}
