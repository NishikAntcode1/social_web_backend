package com.lone_wolf.social_web_app.service;

import com.lone_wolf.social_web_app.entity.Reel;
import com.lone_wolf.social_web_app.entity.User;

import java.util.List;

public interface ReelService {

    Reel createReel(Reel reel, User user);

    List<Reel> findAllReels();

    List<Reel> findUsersReel(Integer userId) throws Exception;
}
