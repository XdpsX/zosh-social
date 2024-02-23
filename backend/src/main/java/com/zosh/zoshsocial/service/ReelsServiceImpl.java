package com.zosh.zoshsocial.service;

import com.zosh.zoshsocial.models.Reels;
import com.zosh.zoshsocial.models.User;
import com.zosh.zoshsocial.repository.ReelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReelsServiceImpl implements ReelsService{

    private final ReelsRepository reelsRepository;
    private final UserService userService;

    @Override
    public Reels createReel(Reels reel, User user) {
        reel.setUser(user);
        return reelsRepository.save(reel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) {
        return reelsRepository.findByUserId(userId);
    }
}
