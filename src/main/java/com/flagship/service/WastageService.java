package com.flagship.service;

import com.flagship.dto.request.WastageAddRequest;
import com.flagship.dto.response.GetAllWastageResponse;
import com.flagship.dto.response.AddWastageResponse;

public interface WastageService {
    AddWastageResponse addWastage(WastageAddRequest wastageAddRequest);

    GetAllWastageResponse getAllWastage();

    GetAllWastageResponse getAllWastageByPeriod(String period);
}
