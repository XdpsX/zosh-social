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

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception {
        User user = userService.findUserById(id);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestHeader("Authorization") String jwt,
                           @RequestBody User user) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user, reqUser.getId());
        return updatedUser;
    }

    @PutMapping("/follow/{userId1}/{userId2}")
    public User followUserHandler(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Integer userId2
    ) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(), userId2);
        return user;
    }

    @GetMapping("/search")
    public List<User> searchUser(
            @RequestParam("query") String query
    ) {
        List<User> users = userService.searchUser(query);
        return users;
    }

    @GetMapping("/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }
}
