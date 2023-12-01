package com.flagship.service.impl;

import com.flagship.dto.request.BrandRequest;
import com.flagship.dto.request.CategoriesRequest;
import com.flagship.dto.request.CountryRequest;
import com.flagship.dto.response.BrandResponse;
import com.flagship.dto.response.CategoriesResponse;
import com.flagship.dto.response.CountryResponse;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.Brand;
import com.flagship.model.db.Categories;
import com.flagship.model.db.Country;
import com.flagship.model.db.User;
import com.flagship.repository.BrandRepository;
import com.flagship.repository.CategoriesRepository;
import com.flagship.repository.CountryRepository;
import com.flagship.repository.UserRepository;
import com.flagship.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonServiceImpl implements CommonService {
    private final BrandRepository brandRepository;
    private final CountryRepository countryRepository;
    private final CategoriesRepository categoriesRepository;
    private final UserRepository userRepository;
    @Autowired
    public CommonServiceImpl(BrandRepository brandRepository, CountryRepository countryRepository,
                             CategoriesRepository categoriesRepository, UserRepository userRepository) {
        this.brandRepository = brandRepository;
        this.countryRepository = countryRepository;
        this.categoriesRepository = categoriesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BrandResponse addBrand(BrandRequest request) {
        Optional<Brand> optionalBrand = brandRepository.findByBrandId(request.getBrandId());
        Optional<User> user = userRepository.findByEmail(request.getUser());
        if (user.isEmpty()) {
            throw new RequestValidationException("User not exist");
        }
        if(optionalBrand.isPresent()){
            throw new RequestValidationException("Brand is exist");
        }else{
            Brand brand = new Brand();
            brand.setBrandId(request.getBrandId());
            brand.setBrandName(request.getBrandName());
            brand.setCreatedBy(user.get());
            brandRepository.save(brand);
            return BrandResponse.from("Brand Added Successfully", brand);
        }
    }

    @Override
    public CountryResponse addCountry(CountryRequest request) {
        Optional<Country> optionalCountry = countryRepository.findByCountryId(request.getCountryId());
        Optional<User> user = userRepository.findByEmail(request.getUser());
        if (user.isEmpty()) {
            throw new RequestValidationException("User not exist");
        }
        if(optionalCountry.isPresent()){
            throw new RequestValidationException("Country is exist");
        }else{
            Country country = new Country();
            country.setCountryId(request.getCountryId());
            country.setCountryName(request.getCountryName());
            country.setCreatedBy(user.get());
            countryRepository.save(country);
            return CountryResponse.from("Country Added Successfully", country);
        }
    }

    @Override
    public CategoriesResponse addCategory(CategoriesRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getUser());
        if (user.isEmpty()) {
            throw new RequestValidationException("User not exist");
        }
        Optional<Categories> optionalCategories = categoriesRepository.findByCategoryId(request.getCategoryId());
        if(optionalCategories.isPresent()){
            throw new RequestValidationException("Category is exist");
        }else{
            Categories categories = new Categories();
            categories.setCategoryId(request.getCategoryId());
            categories.setCategoryName(request.getCategoryName());
            categories.setCreatedBy(user.get());
            categoriesRepository.save(categories);
            return CategoriesResponse.from("Category Added Successfully", categories);
        }
    }
}
