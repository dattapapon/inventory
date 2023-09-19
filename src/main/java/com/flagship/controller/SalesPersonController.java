package com.flagship.controller;

import com.flagship.dto.request.CreateSalesPersonRequest;
import com.flagship.dto.response.CreateSalesPersonResponse;
import com.flagship.dto.response.GetAllSalesPersonResponse;
import com.flagship.dto.response.GetSalesPersonResponse;
import com.flagship.service.SalesPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/salesPerson")
public class SalesPersonController {
    private final SalesPersonService salesPersonService;

    @Autowired
    public SalesPersonController(SalesPersonService salesPersonService) {
        this.salesPersonService = salesPersonService;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateSalesPersonResponse> addSalesPerson(@Valid @NotEmpty @NotNull
                                                                    @RequestBody CreateSalesPersonRequest createSalesPersonRequest) {
        CreateSalesPersonResponse createSalesPersonResponse = salesPersonService.addSalesPerson(createSalesPersonRequest);
        return new ResponseEntity<>(createSalesPersonResponse, HttpStatus.OK);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllSalesPersonResponse> getAllSalesPerson() {
        GetAllSalesPersonResponse getAllSalesPersonResponse = salesPersonService.getAllSalesPerson();
        return new ResponseEntity<>(getAllSalesPersonResponse, HttpStatus.OK);
    }

    @GetMapping(
            value = "/salesPersonId",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetSalesPersonResponse> getCustomer(@RequestParam(name = "salesPersonId") String salesPersonId) {
        GetSalesPersonResponse getSalesPersonResponse = salesPersonService.getSalesPerson(salesPersonId);
        return new ResponseEntity<>(getSalesPersonResponse, HttpStatus.OK);
    }
}
