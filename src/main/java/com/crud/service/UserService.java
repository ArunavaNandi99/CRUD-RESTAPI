package com.crud.service;

import com.crud.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User create(User user);

    List<User> getUsers();

    Optional<User> getUser(Long id);

    void deleteUser(Long id);

    User updateUser(Long id, User user);

    User updateUserFields(Long id, Map<String, Object> fields);
}
