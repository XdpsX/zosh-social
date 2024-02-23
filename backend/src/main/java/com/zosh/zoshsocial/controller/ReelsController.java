package com.zosh.zoshsocial.controller;

import com.zosh.zoshsocial.models.Reels;
import com.zosh.zoshsocial.models.User;
import com.zosh.zoshsocial.service.ReelsService;
import com.zosh.zoshsocial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reels")
public class ReelsController {

    private final ReelsService reelsService;
    private final UserService userService;

    @PostMapping
    public Reels createReels(
            @RequestBody Reels reels,
            @RequestHeader("Authorization") String jwt
    ){
        User reqUser = userService.findUserByJwt(jwt);

        Reels createdReels = reelsService.createReel(reels, reqUser);
        return createdReels;
    }

    @GetMapping
    public List<Reels> findAllReels() {
        List<Reels> reels = reelsService.findAllReels();
        return reels;
    }

    @GetMapping("/user/{userId}")
    public List<Reels> findUsersReels(@PathVariable Integer userId){
        List<Reels> reels = reelsService.findUsersReel(userId);
        return reels;
    }
}
