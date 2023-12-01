package com.flagship.controller;

import com.flagship.dto.request.CustomerRequest;
import com.flagship.dto.response.CustomerResponse;
import com.flagship.dto.response.GetCustomerResponse;
import com.flagship.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @NotEmpty @NotNull
                                                              @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @GetMapping(
            value = "/customerId",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetCustomerResponse> getCustomer(@RequestParam(name = "customerId") String customerId) {
        GetCustomerResponse getSingleCustomer = customerService.getCustomer(customerId);
        return new ResponseEntity<>(getSingleCustomer, HttpStatus.OK);
    }
}
