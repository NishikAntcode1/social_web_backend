package com.lone_wolf.social_web_app.controller;

import com.lone_wolf.social_web_app.entity.Post;
import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.response.ApiResponse;
import com.lone_wolf.social_web_app.service.PostService;
import com.lone_wolf.social_web_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost( @RequestHeader("Authorization") String jwt, @RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post createdPost = postService.createPost(post, reqUser.getId());
        return new ResponseEntity<Post>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = postService.deletePost(reqUser.getId(), userId);
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/posts/save/{postId}")
    public ResponseEntity<Post> savedPost(@PathVariable Integer postId,  @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post> likedPost(@PathVariable Integer postId,  @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
