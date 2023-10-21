package com.flagship.service.impl;

import com.flagship.dto.request.OrderCuttingRequest;
import com.flagship.dto.request.OrderDetailsRequest;
import com.flagship.dto.request.OrderMasterRequest;
import com.flagship.dto.response.AddOrderMasterResponse;
import com.flagship.dto.response.GetOrderCuttingResponse;
import com.flagship.dto.response.GetOrderDetailsResponse;
import com.flagship.dto.response.OrderBillsResponse;
import com.flagship.model.db.OrderCutting;
import com.flagship.model.db.OrderDetails;
import com.flagship.model.db.OrderMaster;
import com.flagship.repository.OrderCuttingRepository;
import com.flagship.repository.OrderDetailsRepository;
import com.flagship.repository.OrderMasterRepository;
import com.flagship.service.OrderService;
import com.flagship.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMasterRepository orderMasterRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderCuttingRepository orderCuttingRepository;

    @Autowired
    public OrderServiceImpl(OrderMasterRepository orderMasterRepository, OrderDetailsRepository orderDetailsRepository,
                            OrderCuttingRepository orderCuttingRepository) {
        this.orderMasterRepository = orderMasterRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderCuttingRepository = orderCuttingRepository;
    }

    @Override
    public AddOrderMasterResponse createOrder(OrderMasterRequest orderMasterRequest) {
        List<OrderDetailsRequest> orderDetailsRequestList = orderMasterRequest.getOrders();
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setSalesPersonName(orderMasterRequest.getSalesPersonName());
        orderMaster.setSupplierCode(orderMasterRequest.getSupplierCode());
        orderMaster.setCustomerName(orderMasterRequest.getCustomerName());
        orderMaster.setPhoneNumber(orderMasterRequest.getPhoneNumber());
        orderMaster.setCustomerStatus(orderMasterRequest.getCustomerStatus());
        orderMaster.setCustomerType(orderMasterRequest.getCustomerType());
        orderMaster.setCompanyName(orderMasterRequest.getCompanyName());
        orderMaster.setOrderDate(DateUtil.getZoneDateTime(orderMasterRequest.getOrderDate() + "T00:00:00"));
        orderMaster.setCreditTerm(DateUtil.getZoneDateTime(orderMasterRequest.getCreditTerm() + "T00:00:00"));
        orderMaster.setOrderId(ZonedDateTime.now().toString());
        OrderMaster saveOrderMaster = orderMasterRepository.save(orderMaster);
        List<GetOrderDetailsResponse> getOrderDetailsResponses = addOrderMaster(saveOrderMaster, orderDetailsRequestList);
        return AddOrderMasterResponse.from("Order create successfully", orderMaster, getOrderDetailsResponses);
    }

    @Override
    public List<OrderBillsResponse> getAllBills() {
        List<OrderBillsResponse> orderBillsResponses = new ArrayList<>();
        List<OrderMaster> orderMasters = orderMasterRepository.findAll();
        for(OrderMaster master : orderMasters){
            List<OrderDetails> orderDetails = orderDetailsRepository.findAllByOrderId(master);
            double bills = 0;
            for(OrderDetails details : orderDetails){
                if(details.getBill() != null){
                    bills = bills + details.getBill();
                }
            }
            OrderBillsResponse orderBillsResponse = OrderBillsResponse.from(master, bills);
            orderBillsResponses.add(orderBillsResponse);
        }
        return orderBillsResponses;
    }

    private List<GetOrderDetailsResponse> addOrderMaster(OrderMaster saveOrderMaster, List<OrderDetailsRequest> orderDetailsRequestList) {
        List<GetOrderDetailsResponse> orderDetailsResponses = new ArrayList<>();
        for (OrderDetailsRequest orderDetailsRequest : orderDetailsRequestList) {
            System.out.println(orderDetailsRequest.getCartonQuantity());
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProductId(orderDetailsRequest.getProductId());
            orderDetails.setProductName(orderDetailsRequest.getProductName());
            orderDetails.setCartonQuantity(orderDetailsRequest.getCartonQuantity());
            orderDetails.setCartonWeight(orderDetailsRequest.getCartonWeight());
            orderDetails.setCartonSellingPrice(orderDetailsRequest.getCartonPrice());
            orderDetails.setPieceQuantity(orderDetailsRequest.getPieceQuantity());
            orderDetails.setPieceWeight(orderDetailsRequest.getPieceWeight());
            orderDetails.setPieceSellingPrice(orderDetailsRequest.getPiecePrice());
            orderDetails.setKgLtQuantity(orderDetailsRequest.getKgLtQuantity());
            orderDetails.setKgLtSellingPrice(orderDetailsRequest.getKgLtPrice());
            orderDetails.setTax(orderDetailsRequest.getTax());
            orderDetails.setVat(orderDetailsRequest.getVat());
            List<OrderCuttingRequest> cuttings = orderDetailsRequest.getCutting();
            Double bill;
            List<GetOrderCuttingResponse> cuttingResponses = new ArrayList<>();
            if (!Objects.isNull(cuttings)) {
                cuttingResponses = addCutting(saveOrderMaster, cuttings);
                bill = calculateBillFromCutting(cuttings);
            } else {
                bill = calculateBill(orderDetailsRequest);
            }
            orderDetails.setBill(bill);
            orderDetails.setOrderId(saveOrderMaster);
            orderDetailsRepository.save(orderDetails);
            orderDetailsResponses.add(GetOrderDetailsResponse.from(orderDetails, cuttingResponses));
        }

        return orderDetailsResponses;
    }

    private List<GetOrderCuttingResponse> addCutting(OrderMaster saveOrderMaster, List<OrderCuttingRequest> cuttings) {
        List<GetOrderCuttingResponse> cuttingResponses = new ArrayList<>();
        for (OrderCuttingRequest cuttingRequest : cuttings) {
            OrderCutting orderCutting = new OrderCutting();
            orderCutting.setCartonNo(cuttingRequest.getCartonNo());
            orderCutting.setCartonQuantity(cuttingRequest.getCartoonQuantity());
            orderCutting.setPieceInCarton(cuttingRequest.getCartoonPiece());
            orderCutting.setPrice(cuttingRequest.getSellingPrice());
            orderCutting.setOrderId(saveOrderMaster);
            orderCuttingRepository.save(orderCutting);
            cuttingResponses.add(GetOrderCuttingResponse.from(orderCutting));
        }
        return cuttingResponses;
    }

    private Double calculateBillFromCutting(List<OrderCuttingRequest> cuttings) {
        Double bill = null;
        for (OrderCuttingRequest orderCuttingRequest : cuttings) {
            bill = orderCuttingRequest.getCartoonQuantity() * orderCuttingRequest.getSellingPrice();
        }
        return bill;
    }

    private Double calculateBill(OrderDetailsRequest orderDetailsRequest) {
        return (orderDetailsRequest.getCartonWeight() * orderDetailsRequest.getCartonPrice()) +
                (orderDetailsRequest.getPieceWeight() * orderDetailsRequest.getPiecePrice()) +
                (orderDetailsRequest.getKgLtQuantity() * orderDetailsRequest.getKgLtPrice());
    }
}
