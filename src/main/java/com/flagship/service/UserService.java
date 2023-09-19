package com.flagship.service;

import com.flagship.dto.response.GetAllUserResponse;

import java.util.List;

public interface UserService {
    List<GetAllUserResponse> getAllUser();
}
