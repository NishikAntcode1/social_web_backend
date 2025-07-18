package com.lone_wolf.social_web_app.repository;

import com.lone_wolf.social_web_app.entity.Reel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelRepository extends JpaRepository<Reel, Integer> {

    public List<Reel> findByUserId(Integer userId);
}
