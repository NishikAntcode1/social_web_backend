package com.lone_wolf.social_web_app.service;

import com.lone_wolf.social_web_app.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    Post createPost(Post post, Integer userId) throws Exception;

    String deletePost(Integer postId, Integer userId) throws Exception;

    List<Post> findPostByUserId(Integer userId);

    Post findPostById(Integer postId) throws Exception;

    List<Post> findAllPost();

    Post savedPost(Integer postId, Integer userId) throws Exception;

    Post likePost(Integer postId, Integer userId) throws Exception;
}
