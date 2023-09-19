package com.flagship.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllWastageResponse {
    private List<GetWastageResponse> getWastageResponseList;

    public static GetAllWastageResponse from(List<GetWastageResponse> getWastageResponseList){
        return GetAllWastageResponse.builder()
                .getWastageResponseList(getWastageResponseList)
                .build();
    }
}
