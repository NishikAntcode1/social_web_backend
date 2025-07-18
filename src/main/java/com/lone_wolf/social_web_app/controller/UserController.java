package com.lone_wolf.social_web_app.controller;

import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.exceptions.UserException;
import com.lone_wolf.social_web_app.repository.UserRepository;
import com.lone_wolf.social_web_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws UserException {
        return userService.findUserById(id);
    }

//    @GetMapping("/users/{email}")
//    public User getUserByEmail(@PathVariable("email") String email) {
//        return userService.findUserByEmail(email);
//    }

    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User updatedUser) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.updateUser(updatedUser, reqUser.getId());
        user.setPassword(null);
        return user;
    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        return userService.followUser(reqUser.getId(), userId2);
    }

    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam String query){
        return userService.searchUser(query);
    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }
}
