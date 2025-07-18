package com.lone_wolf.social_web_app.service;

import com.lone_wolf.social_web_app.entity.Post;
import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.repository.PostRepository;
import com.lone_wolf.social_web_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public Post createPost(Post post, Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (!post.getUser().getId().equals(user.getId())) throw new Exception("Can not delete this post.");

        postRepository.delete(post);

        return "Post deleted successfully.";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> optionalPost = postRepository.findById(postId);

        if(optionalPost.isEmpty()) throw new Exception("Post not found with ID: "+ postId);

        return optionalPost.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)) user.getSavedPost().remove(post);
        else user.getSavedPost().add(post);
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)) post.getLiked().remove(user);
        else post.getLiked().add(user);

        return postRepository.save(post);
    }
}
