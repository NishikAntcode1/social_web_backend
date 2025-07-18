package com.lone_wolf.social_web_app.controller;

import com.lone_wolf.social_web_app.entity.Story;
import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.service.StoryService;
import com.lone_wolf.social_web_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("api/stories")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserByJwt(jwt);
        return storyService.createStory(story, reqUser);
    }

    @GetMapping("api/stories/user/{userId}")
    public List<Story> findUserStory(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        return storyService.findStoryByUserId(userId);
    }

}
