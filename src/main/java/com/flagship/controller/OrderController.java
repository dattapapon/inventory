package com.flagship.controller;

import com.flagship.dto.request.OrderMasterRequest;
import com.flagship.dto.response.AddOrderMasterResponse;
import com.flagship.dto.response.OrderBillsResponse;
import com.flagship.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    private ResponseEntity<AddOrderMasterResponse> addOrder(@Valid @NotNull @RequestBody OrderMasterRequest orderMasterRequest) {
        AddOrderMasterResponse orderMasterResponse = orderService.createOrder(orderMasterRequest);
        return new ResponseEntity<>(orderMasterResponse, HttpStatus.OK);
    }

//    @GetMapping(
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//
//    private ResponseEntity<List<OrderBillsResponse>> getAllBills() {
//        List<OrderBillsResponse> orderBillsResponseList = orderService.getAllBills();
//        return new ResponseEntity<>(orderBillsResponseList, HttpStatus.OK);
//    }

    @GetMapping(
            value = "/time",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    private ResponseEntity<List<OrderBillsResponse>> getAllBillsByTime(@RequestParam(value = "start") String start,
                                                                       @RequestParam(value = "end") String end) {
        List<OrderBillsResponse> orderBillsResponseList = orderService.getAllBillsByTime(start, end);
        return new ResponseEntity<>(orderBillsResponseList, HttpStatus.OK);
    }

    @GetMapping(
            value = "/bills",
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    private ResponseEntity<List<OrderBillsResponse>> getAllBills() {
        List<OrderBillsResponse> orderBillsResponseList = orderService.getAllBills();
        return new ResponseEntity<>(orderBillsResponseList, HttpStatus.OK);
    }
}
