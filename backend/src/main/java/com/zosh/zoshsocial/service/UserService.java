package com.zosh.zoshsocial.service;

import com.zosh.zoshsocial.models.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
    User registerUser(User user);
    User findUserById(Integer userId) throws Exception;
    User findUserByEmail(String email);
    User followUser(Integer userId1, Integer userId2) throws Exception;
    User updateUser(User user, Integer userId) throws Exception;
    List<User> searchUser(String query);
}
