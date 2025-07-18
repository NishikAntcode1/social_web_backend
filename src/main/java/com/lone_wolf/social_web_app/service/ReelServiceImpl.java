package com.lone_wolf.social_web_app.service;

import com.lone_wolf.social_web_app.entity.Reel;
import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.repository.ReelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelServiceImpl implements ReelService{

    @Autowired
    private ReelRepository reelRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reel createReel(Reel reel, User user) {
        Reel createdReel = new Reel();
        createdReel.setTitle(reel.getTitle());
        createdReel.setVideo(reel.getVideo());
        createdReel.setUser(reel.getUser());

        return reelRepository.save(reel);
    }

    @Override
    public List<Reel> findAllReels() {
        return reelRepository.findAll();
    }

    @Override
    public List<Reel> findUsersReel(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelRepository.findByUserId(userId);
    }
}
