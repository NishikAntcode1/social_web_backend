package com.lone_wolf.social_web_app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String caption;
    private String image;
    private String video;
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @OneToMany
    private List<User> liked = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(Integer id, String caption, String image, LocalDateTime createdAt, String video, List<User> liked, User user, List<Comment> comments) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.createdAt = createdAt;
        this.video = video;
        this.liked = liked;
        this.user = user;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLiked() {
        return liked;
    }

    public void setLiked(List<User> liked) {
        this.liked = liked;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
