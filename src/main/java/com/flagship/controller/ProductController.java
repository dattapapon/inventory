package com.flagship.controller;

import com.flagship.dto.request.ImportRequest;
import com.flagship.dto.request.ProductRequest;
import com.flagship.dto.response.AllProductResponse;
import com.flagship.dto.response.AllStockResponse;
import com.flagship.dto.response.ImportResponse;
import com.flagship.dto.response.ProductResponse;
import com.flagship.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductResponse> createProduct(@Valid @NotNull @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.createProduct(productRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            value = "/import",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ImportResponse> addImport(@Valid @NotNull @RequestBody ImportRequest importRequest) {
        ImportResponse response = productService.addImport(importRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllProductResponse> getProduct() {
        AllProductResponse allProduct = productService.getProduct();
        return new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    @GetMapping(
            value = "/stock",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AllStockResponse> getStock() {
        AllStockResponse allStockResponse = productService.getStock();
        return new ResponseEntity<>(allStockResponse, HttpStatus.OK);
    }
}
