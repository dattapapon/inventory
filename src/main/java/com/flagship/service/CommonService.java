package com.flagship.service;

import com.flagship.dto.request.*;
import com.flagship.dto.response.*;

public interface CommonService {
  BrandResponse addBrand(BrandRequest request);

  CountryResponse addCountry(CountryRequest request);

  CategoriesResponse addCategory(CategoriesRequest request);

  SaleResponse addSale(SaleRequest request);

  BranchResponse addBranch(BranchRequest request);

  SupplierResponse addSupplier(SupplierRequest request);

  AllProductResponse getProduct();

  AllStockResponse getStock();

  ProductResponse createProduct(ProductRequest productRequest);

  AllBrandResponse getBrand();

  AllCountryResponse getCountry();

  AllCategoryResponse getCategory();

  SingleSale getSale(String product, String supplier);

  GetAllSuppliers getSupplier();

  CustomerResponse addCustomer(CustomerRequest customerRequest);

  GetAllCustomer getCustomer();

  AllBranchResponse getBranch(String supplier);

  SalesPersonResponse addSalesPerson(SalesPersonRequest salesPersonRequest);

  AllSalesPersonResponse getSalesPerson();

  WastageResponse addWastage(WastageAddRequest wastageAddRequest);

  ReturnResponse addReturn(ReturnRequest request);

  AllProductRevenueResponse calculateRevenue();

  FinalResponse getAllProductAndArticleAndSale();

  AllWastageResponse getAllWastage();
}
