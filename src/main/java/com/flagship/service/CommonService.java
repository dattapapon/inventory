package com.flagship.service;

import com.flagship.dto.request.BrandRequest;
import com.flagship.dto.request.CategoriesRequest;
import com.flagship.dto.request.CountryRequest;
import com.flagship.dto.response.BrandResponse;
import com.flagship.dto.response.CategoriesResponse;
import com.flagship.dto.response.CountryResponse;

public interface CommonService {
    BrandResponse addBrand(BrandRequest request);

    CountryResponse addCountry(CountryRequest request);

    CategoriesResponse addCategory(CategoriesRequest request);
}
