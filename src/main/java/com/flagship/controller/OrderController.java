package com.flagship.controller;

import com.flagship.dto.request.OrderMasterRequest;
import com.flagship.dto.response.AddOrderMasterResponse;
import com.flagship.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )

    private ResponseEntity<AddOrderMasterResponse> addOrder(@Valid @NotNull @RequestBody OrderMasterRequest orderMasterRequest){
        AddOrderMasterResponse orderMasterResponse = orderService.createOrder(orderMasterRequest);
        return new ResponseEntity<>(orderMasterResponse, HttpStatus.OK);
    }
}
