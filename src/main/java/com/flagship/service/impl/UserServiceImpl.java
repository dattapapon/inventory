package com.flagship.service.impl;

import com.flagship.dto.response.GetAllUserResponse;
import com.flagship.model.db.User;
import com.flagship.repository.UserRepository;
import com.flagship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<GetAllUserResponse> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<GetAllUserResponse> getAllUserResponses = new ArrayList<>();
        for (User user : userList) {
            getAllUserResponses.add(GetAllUserResponse.from(user));
        }
        return getAllUserResponses;
    }
}
