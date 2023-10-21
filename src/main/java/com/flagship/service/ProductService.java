package com.flagship.service;

import com.flagship.dto.request.AddImportRequest;
import com.flagship.dto.request.ProductRequest;
import com.flagship.dto.response.*;

import java.util.List;

public interface ProductService {
    CreateProductResponse createProduct(ProductRequest productRequest);
    GetAllProductResponse getProduct();

    AddImportResponse addImport(AddImportRequest addImportRequest);

    GetAllImportResponse getSingleProduct(String productId);

    GetAllImportResponse getAllProduct();

    List<GetCuttingResponse> getCuttingDetails(Long cuttingId);

    List<GetAllCuttingResponse> getAllCuttingDetails();

    List<RevenueResponse> getRevenueDetails();
}
