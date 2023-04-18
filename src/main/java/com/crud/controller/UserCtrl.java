package com.crud.controller;

import com.crud.model.User;
import com.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UserCtrl {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser (@RequestBody User user){
        return userService.create(user);
    }
    @GetMapping("/")
    public List<User> getUsers (){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return  userService.updateUser(id,user);
    }

    @PatchMapping("/{id}")
    public User updateUserFields(@PathVariable Long id ,@RequestBody Map<String , Object> fields){
        return  userService.updateUserFields(id, fields);
    }
}
