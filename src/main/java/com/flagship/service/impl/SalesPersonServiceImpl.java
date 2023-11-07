package com.flagship.service.impl;

import com.flagship.dto.request.CreateSalesPersonRequest;
import com.flagship.dto.response.CreateSalesPersonResponse;
import com.flagship.dto.response.GetAllSalesPersonResponse;
import com.flagship.dto.response.GetSalesPersonResponse;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.SalesPerson;
import com.flagship.model.db.User;
import com.flagship.repository.SalesPersonRepository;
import com.flagship.repository.UserRepository;
import com.flagship.service.SalesPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesPersonServiceImpl implements SalesPersonService {
    private final SalesPersonRepository salesPersonRepository;
    private final UserRepository userRepository;

    @Autowired
    public SalesPersonServiceImpl(SalesPersonRepository salesPersonRepository, UserRepository userRepository) {
        this.salesPersonRepository = salesPersonRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CreateSalesPersonResponse addSalesPerson(CreateSalesPersonRequest createSalesPersonRequest) {
        createSalesPersonRequest.validate();
        Boolean checkSalesPersonExist = salesPersonExist(createSalesPersonRequest.getSalesPersonId());
        if (checkSalesPersonExist) {
            throw new RequestValidationException("Sales Person id is exist");
        }
        Optional<User> user = userRepository.findByEmail(createSalesPersonRequest.getUserEmail());
        if (user.isEmpty()) {
            throw new RequestValidationException("User not exist");
        }
        SalesPerson salesPerson = new SalesPerson();
        salesPerson.setSalesPersonId(createSalesPersonRequest.getSalesPersonId());
        salesPerson.setSalesPersonName(createSalesPersonRequest.getSalesPersonName());
        salesPerson.setPhoneNumber(createSalesPersonRequest.getPhoneNumber());
        salesPerson.setArea(createSalesPersonRequest.getArea());
        salesPerson.setCreatedBy(user.get());
        salesPersonRepository.save(salesPerson);
        return CreateSalesPersonResponse.from("Customer added Successfully", salesPerson);
    }

    @Override
    public GetAllSalesPersonResponse getAllSalesPerson() {
        List<SalesPerson> salesPersonList = salesPersonRepository.findAll();
        List<GetSalesPersonResponse> getSalesPersonResponses = new ArrayList<>();
        for (SalesPerson salesPerson : salesPersonList) {
            getSalesPersonResponses.add(GetSalesPersonResponse.from(salesPerson));
        }
        return GetAllSalesPersonResponse.from(getSalesPersonResponses);
    }

    @Override
    public GetSalesPersonResponse getSalesPerson(String salesPersonId) {
        Optional<SalesPerson> salesPerson = salesPersonRepository.findBySalesPersonId(salesPersonId);
        if (salesPerson.isEmpty()) {
            throw new RequestValidationException("Sales Person is not exist");
        }
        return GetSalesPersonResponse.from(salesPerson.get());
    }

    private Boolean salesPersonExist(String salesPersonId) {
        Optional<SalesPerson> salesPerson = salesPersonRepository.findBySalesPersonId(salesPersonId);
        return salesPerson.isPresent();
    }
}
