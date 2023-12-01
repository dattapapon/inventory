package com.flagship.dto.response;

import com.flagship.model.db.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAllUserResponse {
    private Long userId;
    private String name;

    public static GetAllUserResponse from(User user) {
        return GetAllUserResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .build();
    }
}
