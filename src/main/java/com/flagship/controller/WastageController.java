package com.flagship.controller;

import com.flagship.dto.request.WastageAddRequest;
import com.flagship.dto.response.AddWastageResponse;
import com.flagship.dto.response.GetAllWastageResponse;
import com.flagship.service.WastageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/wastage")
public class WastageController {
    private final WastageService wastageService;
    @Autowired
    public WastageController(WastageService wastageService) {
        this.wastageService = wastageService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AddWastageResponse> addWastage(@Valid @NotNull @RequestBody WastageAddRequest wastageAddRequest){
        AddWastageResponse addWastageResponse = wastageService.addWastage(wastageAddRequest);
        return new ResponseEntity<>(addWastageResponse, HttpStatus.OK);
    }
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllWastageResponse> getAllWastage(){
        GetAllWastageResponse getAllWastageResponse = wastageService.getAllWastage();
        return new ResponseEntity<>(getAllWastageResponse, HttpStatus.OK);
    }

    @GetMapping(
            value = "/period",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllWastageResponse> getAllWastageByPeriod(@RequestParam(name = "Period") String period){
        GetAllWastageResponse getAllWastageResponse = wastageService.getAllWastageByPeriod(period);
        return new ResponseEntity<>(getAllWastageResponse, HttpStatus.OK);
    }
}
