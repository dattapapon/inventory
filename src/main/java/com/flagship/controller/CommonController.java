package com.flagship.controller;

import com.flagship.dto.request.BrandRequest;
import com.flagship.dto.request.CategoriesRequest;
import com.flagship.dto.request.CountryRequest;
import com.flagship.dto.response.BrandResponse;
import com.flagship.dto.response.CategoriesResponse;
import com.flagship.dto.response.CountryResponse;
import com.flagship.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/common")
public class CommonController {
    private final CommonService commonService;

    @Autowired
    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @PostMapping(
            value = "/brand",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BrandResponse> addBrand(@Valid @NotNull @RequestBody BrandRequest request) {
        BrandResponse response = commonService.addBrand(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            value = "/country",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryResponse> addCountry(@Valid @NotNull @RequestBody CountryRequest request) {
        CountryResponse response = commonService.addCountry(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            value = "/category",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoriesResponse> addCategory(@Valid @NotNull @RequestBody CategoriesRequest request) {
        CategoriesResponse response = commonService.addCategory(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
