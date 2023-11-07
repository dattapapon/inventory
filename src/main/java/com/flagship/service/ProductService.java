package com.flagship.service;

import com.flagship.dto.request.AddImportRequest;
import com.flagship.dto.request.MoveProductRequest;
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

    GetAllImportResponse getAllProductByTime(String productId, String warhouse, String start, String end);

    List<GetAllCuttingResponse> getAllCuttingDetailsByTime(String productId, String warhouse, String start, String end);

    List<RevenueResponse> getRevenueDetailsByTime(String start, String end);

    SuccessResponse moveProduct(MoveProductRequest moveProductRequest);
}
