package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllCustomerResponse {
    List<GetCustomerResponse> getAllCustomerList;
    public static GetAllCustomerResponse from(List<GetCustomerResponse> getAllCustomerList) {
        return GetAllCustomerResponse.builder()
                .getAllCustomerList(getAllCustomerList)
                .build();
    }
}
