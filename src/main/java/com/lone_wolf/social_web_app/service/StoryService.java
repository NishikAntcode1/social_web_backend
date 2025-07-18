package com.lone_wolf.social_web_app.service;

import com.lone_wolf.social_web_app.entity.Story;
import com.lone_wolf.social_web_app.entity.User;

import java.util.List;

public interface StoryService {
    Story createStory(Story story, User user);

    List<Story> findStoryByUserId(Integer userId) throws Exception;
}
