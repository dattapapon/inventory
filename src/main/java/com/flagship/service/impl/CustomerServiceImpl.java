package com.flagship.service.impl;

import com.flagship.dto.request.CreateCustomerRequest;
import com.flagship.dto.request.CreateSalesPersonRequest;
import com.flagship.dto.response.CreateCustomerResponse;
import com.flagship.dto.response.GetAllCustomerResponse;
import com.flagship.dto.response.GetCustomerResponse;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.Customer;
import com.flagship.model.db.User;
import com.flagship.repository.CustomerRepository;
import com.flagship.repository.UserRepository;
import com.flagship.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CreateCustomerResponse addCustomer(CreateCustomerRequest createCustomerRequest) {
        createCustomerRequest.validate();
        Boolean checkCustomerExist = customerExist(createCustomerRequest.getCustomerId());
        if(checkCustomerExist){
            throw  new RequestValidationException("Customer id is exist");
        }
        Optional<User> user = userRepository.findByEmail(createCustomerRequest.getUserEmail());
        if(user.isEmpty()){
            throw new RequestValidationException("User not exist");
        }
        Customer customer = new Customer();
        customer.setCustomerId(createCustomerRequest.getCustomerId());
        customer.setCustomerName(createCustomerRequest.getCustomerName());
        customer.setCompany(createCustomerRequest.getCompany());
        customer.setPhoneNumber(createCustomerRequest.getPhoneNumber());
        customer.setEmail(createCustomerRequest.getEmail());
        customer.setCreatedBy(user.get());
        customerRepository.save(customer);
        return CreateCustomerResponse.from("Customer added Successfully", customer);
    }

    @Override
    public GetAllCustomerResponse getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        List<GetCustomerResponse> getCustomerResponses = new ArrayList<>();
        for(Customer customer : customerList){
            getCustomerResponses.add(GetCustomerResponse.from(customer));
        }
        return GetAllCustomerResponse.from(getCustomerResponses);
    }

    @Override
    public GetCustomerResponse getCustomer(String customerId) {
        Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
        if(customer.isEmpty()){
            throw new RequestValidationException("Customer is not exist");
        }
        return GetCustomerResponse.from(customer.get());
    }

    private Boolean customerExist(String customerId) {
        Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
        return customer.isPresent();
    }
}
