package com.lone_wolf.social_web_app.controller;

import com.lone_wolf.social_web_app.entity.Reel;
import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.service.ReelService;
import com.lone_wolf.social_web_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelController {

    @Autowired
    private ReelService reelService;

    @Autowired
    private UserService userService;

    @PostMapping("api/reels")
    public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt(jwt);
        return reelService.createReel(reel, reqUser);
    }

    @GetMapping("api/reels")
    public List<Reel> findAllReels() {
        return reelService.findAllReels();
    }

    @GetMapping("api/reels/user/{userId}")
    public List<Reel> findUserReels(@PathVariable("userId") Integer userId) throws Exception {
        return reelService.findUsersReel(userId);
    }
}
