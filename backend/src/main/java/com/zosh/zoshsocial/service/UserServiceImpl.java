package com.zosh.zoshsocial.service;

import com.zosh.zoshsocial.config.JwtProvider;
import com.zosh.zoshsocial.models.User;
import com.zosh.zoshsocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User user) {
        User isExist = userRepository.findByEmail(user.getEmail());
        if (isExist != null){
            throw new RuntimeException("email is already used with another account");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public User followUser(Integer reqUserId, Integer userId2) throws Exception {
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);
        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);
        return reqUser;
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
        if (user.getGender() != null){
            oldUser.setGender(user.getGender());
        }
        User updatedUser = userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }
}
