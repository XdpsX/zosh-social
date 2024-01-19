package com.zosh.zoshsocial.controller;

import com.zosh.zoshsocial.models.User;
import com.zosh.zoshsocial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        User savedUser = userService.registerUser(user);
        return savedUser;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        User user = userService.findUserById(id);
        return user;
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Integer userId,
                           @RequestBody User user) throws Exception {
        User updatedUser = userService.updateUser(user, userId);
        return updatedUser;
    }

    @PutMapping("/follow/{userId1}/{userId2}")
    public User followUserHandler(
            @PathVariable Integer userId1,
            @PathVariable Integer userId2
    ) throws Exception {
        User user = userService.followUser(userId1, userId2);
        return user;
    }

    @GetMapping("/search")
    public List<User> searchUser(
            @RequestParam("query") String query
    ) {
        List<User> users = userService.searchUser(query);
        return users;
    }
}
