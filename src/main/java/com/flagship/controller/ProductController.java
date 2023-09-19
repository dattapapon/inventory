package com.flagship.controller;

import com.flagship.dto.request.AddImportRequest;
import com.flagship.dto.request.ProductRequest;
import com.flagship.dto.response.*;
import com.flagship.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    public ResponseEntity<CreateProductResponse> createProduct(@Valid @NotNull @RequestBody ProductRequest productRequest) {
        CreateProductResponse response = productService.createProduct(productRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(
            value = "/import",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AddImportResponse> addImport(@Valid @NotNull @RequestBody AddImportRequest addImportRequest) {
        AddImportResponse response = productService.addImport(addImportRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllProductResponse> getProduct() {
        GetAllProductResponse allProduct = productService.getProduct();
        return new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    @GetMapping(
            value="/productId",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllImportResponse> getSingleProduct(@RequestParam(value = "productId") String productId) {
        GetAllImportResponse getImportResponseList = productService.getSingleProduct(productId);
        return new ResponseEntity<>(getImportResponseList, HttpStatus.OK);
    }

    @GetMapping(
            value="/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllImportResponse> getAllProduct() {
        GetAllImportResponse getImportResponseList = productService.getAllProduct();
        return new ResponseEntity<>(getImportResponseList, HttpStatus.OK);
    }

    @GetMapping(
            value="/cutting",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GetCuttingResponse>> getCuttingDetails(@RequestParam(value = "cuttingId") Long cuttingId) {
        List<GetCuttingResponse> getCuttingResponses = productService.getCuttingDetails(cuttingId);
        return new ResponseEntity<>(getCuttingResponses, HttpStatus.OK);
    }
}
