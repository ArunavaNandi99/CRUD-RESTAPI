package com.crud.service;

import com.crud.model.User;
import com.crud.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public User create(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
         User existingUser  = userRepo.findById(id).orElse(null);
        existingUser.setFname(user.getFname());
         existingUser.setLname(user.getLname());
         existingUser.setAge(user.getAge());
         existingUser.setState(user.getState());
         existingUser.setCountry(user.getCountry());
        return userRepo.save(existingUser);
    }

    @Override
    public User updateUserFields(Long id, Map<String, Object> fields) {
        Optional<User> existingUser  = userRepo.findById(id);
        if(existingUser.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(User.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser.get(), value);
            });

            return userRepo.save(existingUser.get());
        }
        return null;
    }
}
