package com.flagship.service;

import com.flagship.dto.request.WastageAddRequest;
import com.flagship.dto.response.AddWastageResponse;
import com.flagship.dto.response.GetAllWastageResponse;

public interface WastageService {
    AddWastageResponse addWastage(WastageAddRequest wastageAddRequest);

    GetAllWastageResponse getAllWastage();

    GetAllWastageResponse getAllWastageByPeriod(String period);
}
