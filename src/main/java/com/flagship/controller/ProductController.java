package com.flagship.controller;

import com.flagship.dto.request.AddImportRequest;
import com.flagship.dto.request.MoveProductRequest;
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
            value = "/productId",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllImportResponse> getSingleProduct(@RequestParam(value = "productId") String productId) {
        GetAllImportResponse getImportResponseList = productService.getSingleProduct(productId);
        return new ResponseEntity<>(getImportResponseList, HttpStatus.OK);
    }

    @GetMapping(
            value = "/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllImportResponse> getAllProductByTime(@RequestParam(name = "productId", required = false) String productId,
                                                                    @RequestParam(name = "warhouse", required = false) String warhouse,
                                                                    @RequestParam(name = "start", required = false) String start,
                                                                    @RequestParam(name = "end", required = false) String end) {
        GetAllImportResponse getImportResponseList = productService.getAllProductByTime(productId, warhouse, start, end);
        return new ResponseEntity<>(getImportResponseList, HttpStatus.OK);
    }

    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllImportResponse> getAllProduct() {
        GetAllImportResponse getImportResponseList = productService.getAllProduct();
        return new ResponseEntity<>(getImportResponseList, HttpStatus.OK);
    }

    @GetMapping(
            value = "/cutting",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GetCuttingResponse>> getCuttingDetails(@RequestParam(value = "cuttingId") Long cuttingId) {
        List<GetCuttingResponse> getCuttingResponses = productService.getCuttingDetails(cuttingId);
        return new ResponseEntity<>(getCuttingResponses, HttpStatus.OK);
    }

    @GetMapping(
            value = "/cutting/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GetAllCuttingResponse>> getAllCuttingDetailsByTime(@RequestParam(value = "productId", required = false) String productId,
                                                                                  @RequestParam(value = "warhouse", required = false) String warhouse,
                                                                                  @RequestParam(value = "start") String start,
                                                                                  @RequestParam(value = "end") String end) {
        List<GetAllCuttingResponse> allCuttingResponse = productService.getAllCuttingDetailsByTime(productId, warhouse, start, end);
        return new ResponseEntity<>(allCuttingResponse, HttpStatus.OK);
    }

    @GetMapping(
            value = "/revenue",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<RevenueResponse>> getRevenueDetails() {
        List<RevenueResponse> revenueResponses = productService.getRevenueDetails();
        return new ResponseEntity<>(revenueResponses, HttpStatus.OK);
    }

    @GetMapping(
            value = "/revenue/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<RevenueResponse>> getRevenueDetailsByTime(@RequestParam(value = "start") String start,
                                                                         @RequestParam(value = "end") String end) {
        List<RevenueResponse> revenueResponses = productService.getRevenueDetailsByTime(start, end);
        return new ResponseEntity<>(revenueResponses, HttpStatus.OK);
    }

    @GetMapping(
            value = "/cutting/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GetAllCuttingResponse>> getAllCuttingDetails() {
        List<GetAllCuttingResponse> allCuttingResponse = productService.getAllCuttingDetails();
        return new ResponseEntity<>(allCuttingResponse, HttpStatus.OK);
    }

    @PostMapping(
            value = "/move",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<SuccessResponse> moveProduct(@Valid @NotNull @RequestBody MoveProductRequest moveProductRequest) {
        SuccessResponse response = productService.moveProduct(moveProductRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
