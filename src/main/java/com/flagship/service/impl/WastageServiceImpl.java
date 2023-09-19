package com.flagship.service.impl;

import com.flagship.constant.enums.Cause;
import com.flagship.dto.request.WastageAddRequest;
import com.flagship.dto.response.GetAllWastageResponse;
import com.flagship.dto.response.AddWastageResponse;
import com.flagship.dto.response.GetWastageResponse;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.User;
import com.flagship.model.db.Wastage;
import com.flagship.repository.UserRepository;
import com.flagship.repository.WastageRepository;
import com.flagship.service.WastageService;
import com.flagship.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WastageServiceImpl implements WastageService {
    private final WastageRepository wastageRepository;
    private final UserRepository userRepository;
    @Autowired
    public WastageServiceImpl(WastageRepository wastageRepository, UserRepository userRepository) {
        this.wastageRepository = wastageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddWastageResponse addWastage(WastageAddRequest wastageAddRequest) {
        Wastage wastage = new Wastage();
        Optional<User> user = userRepository.findByEmail(wastageAddRequest.getUserEmail());
        if(user.isEmpty()){
            throw new RequestValidationException("User not exist");
        }
        wastage.setProductId(wastageAddRequest.getProductId());
        wastage.setProductName(wastageAddRequest.getProductName());
        wastage.setProductSerialNo(wastageAddRequest.getProductSerialNo());
        wastage.setWarHouse(wastageAddRequest.getWarHouse());
        wastage.setImportDate(DateUtil.getZoneDateTime(wastageAddRequest.getImportDate() + "T00:00:00"));
        wastage.setCause(Cause.fromName(wastageAddRequest.getCause().toString()));
        wastage.setCreatedBy(user.get());
        wastageRepository.save(wastage);
        return AddWastageResponse.from("Wastage add successfully", wastage);
    }

    @Override
    public GetAllWastageResponse getAllWastage() {
        List<Wastage> wastageList = wastageRepository.findAll();
        List<GetWastageResponse> getWastageResponseList = new ArrayList<>();
        for(Wastage wastage : wastageList){
            getWastageResponseList.add(GetWastageResponse.from(wastage));
        }
        return GetAllWastageResponse.from(getWastageResponseList);
    }

    @Override
    public GetAllWastageResponse getAllWastageByPeriod(String period) {
        switch (period) {
            case "all":
                return getAllWastage();
            case "daily": {
                String today = DateUtil.getFormattedDate(ZonedDateTime.now());
                ZonedDateTime start = DateUtil.getZoneDateTime(today + "T00:00:00");
                ZonedDateTime end = DateUtil.getZoneDateTime(today + "T23:59:59");
                List<Wastage> wastageList = wastageRepository.findByCreatedOnBetweenOrderByCreatedOnAsc(start, end);
                List<GetWastageResponse> getWastageResponseList = new ArrayList<>();
                for (Wastage wastage : wastageList) {
                    getWastageResponseList.add(GetWastageResponse.from(wastage));
                }
                return GetAllWastageResponse.from(getWastageResponseList);
            }
            case "weekly": {
                String start = DateUtil.getFormattedDate(ZonedDateTime.now());
                String end = DateUtil.getFormattedDate(ZonedDateTime.now().minusDays(6));
                ZonedDateTime startDate = DateUtil.getZoneDateTime(end + "T00:00:00");
                ZonedDateTime endDate = DateUtil.getZoneDateTime(start + "T23:59:59");
                List<Wastage> wastageList = wastageRepository.findByCreatedOnBetweenOrderByCreatedOnAsc(startDate, endDate);
                List<GetWastageResponse> getWastageResponseList = new ArrayList<>();
                for (Wastage wastage : wastageList) {
                    getWastageResponseList.add(GetWastageResponse.from(wastage));
                }
                return GetAllWastageResponse.from(getWastageResponseList);
            }
            case "monthly": {
                String start = DateUtil.getFormattedDate(ZonedDateTime.now());
                String end = DateUtil.getFormattedDate(ZonedDateTime.now().minusDays(29));
                ZonedDateTime startDate = DateUtil.getZoneDateTime(end + "T00:00:00");
                ZonedDateTime endDate = DateUtil.getZoneDateTime(start + "T23:59:59");
                List<Wastage> wastageList = wastageRepository.findByCreatedOnBetweenOrderByCreatedOnAsc(startDate, endDate);
                List<GetWastageResponse> getWastageResponseList = new ArrayList<>();
                for (Wastage wastage : wastageList) {
                    getWastageResponseList.add(GetWastageResponse.from(wastage));
                }
                return GetAllWastageResponse.from(getWastageResponseList);
            }
            default: {
                String start = DateUtil.getFormattedDate(ZonedDateTime.now());
                String end = DateUtil.getFormattedDate(ZonedDateTime.now().minusDays(364));
                ZonedDateTime startDate = DateUtil.getZoneDateTime(end + "T00:00:00");
                ZonedDateTime endDate = DateUtil.getZoneDateTime(start + "T23:59:59");
                List<Wastage> wastageList = wastageRepository.findByCreatedOnBetweenOrderByCreatedOnAsc(startDate, endDate);
                List<GetWastageResponse> getWastageResponseList = new ArrayList<>();
                for (Wastage wastage : wastageList) {
                    getWastageResponseList.add(GetWastageResponse.from(wastage));
                }
                return GetAllWastageResponse.from(getWastageResponseList);
            }
        }
    }
}
