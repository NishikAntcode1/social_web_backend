package com.lone_wolf.social_web_app.repository;

import com.lone_wolf.social_web_app.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
