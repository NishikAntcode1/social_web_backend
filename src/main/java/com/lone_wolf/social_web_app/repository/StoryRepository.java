package com.lone_wolf.social_web_app.repository;

import com.lone_wolf.social_web_app.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
    List<Story> findByUserId(Integer userId);
}
