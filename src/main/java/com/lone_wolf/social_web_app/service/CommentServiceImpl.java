package com.lone_wolf.social_web_app.service;

import com.lone_wolf.social_web_app.entity.Comment;
import com.lone_wolf.social_web_app.entity.Post;
import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.repository.CommentRepository;
import com.lone_wolf.social_web_app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(comment);
        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()) throw new Exception("Comment Not Found");
        return  optionalComment.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) throws Exception {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if(!comment.getLiked().contains(user)) comment.getLiked().add(user);
        else comment.getLiked().remove(user);

        return commentRepository.save(comment);
    }
}
