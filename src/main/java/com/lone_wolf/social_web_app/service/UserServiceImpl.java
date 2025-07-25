package com.lone_wolf.social_web_app.service;

import com.lone_wolf.social_web_app.config.JwtProvider;
import com.lone_wolf.social_web_app.entity.User;
import com.lone_wolf.social_web_app.exceptions.UserException;
import com.lone_wolf.social_web_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserById(Integer userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new UserException("User not exist with userId :" + userId);
        return user.get();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser = findUserById(reqUserId);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer userId) throws UserException {
        Optional<User> user1 = userRepository.findById(userId);

        if(user1.isEmpty()) throw new UserException("User doesn't exist with id : "+ userId);

        User oldUser = user1.get();

        if (user.getFirstName() != null) oldUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) oldUser.setLastName(user.getLastName());
        if (user.getEmail() != null) oldUser.setEmail(user.getEmail());
        if (user.getGender() != null) oldUser.setGender(user.getGender());

        return userRepository.save(oldUser);

    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        return userRepository.findByEmail(email);
    }
}
