package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class GetUsers {
    private Integer code;
    private List<GetAllUserResponse> getAllUserResponseList;

    public static GetUsers from(List<GetAllUserResponse> getAllUserResponseList){
        return GetUsers.builder()
                .code(HttpStatus.SC_OK)
                .getAllUserResponseList(getAllUserResponseList)
                .build();
    }
}
