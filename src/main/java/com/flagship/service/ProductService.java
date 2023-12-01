package com.flagship.service;

import com.flagship.dto.request.ImportRequest;
import com.flagship.dto.request.ProductRequest;
import com.flagship.dto.response.AllProductResponse;
import com.flagship.dto.response.AllStockResponse;
import com.flagship.dto.response.ImportResponse;
import com.flagship.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    AllProductResponse getProduct();

    ImportResponse addImport(ImportRequest importRequest);

    AllStockResponse getStock();
}
