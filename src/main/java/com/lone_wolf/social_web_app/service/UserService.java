package com.lone_wolf.social_web_app.service;

import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.exceptions.UserException;

import java.util.List;
import java.util.Optional;

public interface UserService {
//    User registerUser(User user);

    User findUserById(Integer userId) throws UserException;

    User findUserByEmail(String email);

    User followUser(Integer userId1, Integer userId2) throws UserException;

    User updateUser(User user, Integer userId) throws UserException;

    List<User> searchUser(String query);

    User findUserByJwt(String jwt);

}
