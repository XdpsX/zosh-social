package com.zosh.zoshsocial.service;

import com.zosh.zoshsocial.models.Reels;
import com.zosh.zoshsocial.models.User;

import java.util.List;

public interface ReelsService {

    Reels createReel(Reels reel, User user);
    List<Reels> findAllReels();
    List<Reels> findUsersReel(Integer userId);
}
