package com.zosh.zoshsocial.service;

import com.zosh.zoshsocial.models.User;
import com.zosh.zoshsocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            return user.get();
        }
        throw new Exception("User not exist with user Id = " + userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {
        Optional<User> user1 = userRepository.findById(userId);
        if (user1.isEmpty()){
            throw new Exception("User not exit with id = " + userId);
        }
        User oldUser = user1.get();
        if (user.getFirstName() != null){
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null){
            oldUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null){
            oldUser.setEmail(user.getEmail());
        }
        User updatedUser = userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
