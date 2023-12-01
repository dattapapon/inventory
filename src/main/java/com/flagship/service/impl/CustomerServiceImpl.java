package com.flagship.service.impl;

import com.flagship.constant.enums.CustomerType;
import com.flagship.dto.request.CustomerRequest;
import com.flagship.dto.response.CustomerResponse;
import com.flagship.dto.response.GetCustomerResponse;
import com.flagship.exception.RequestValidationException;
import com.flagship.model.db.Customer;
import com.flagship.model.db.User;
import com.flagship.repository.CustomerRepository;
import com.flagship.repository.UserRepository;
import com.flagship.service.CustomerService;
import org.springframework.stereotype.Service;

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
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        customerRequest.validate();
        Boolean checkCustomerExist = customerExist(customerRequest.getCustomerId());
        if (checkCustomerExist) {
            throw new RequestValidationException("Customer id is exist");
        }
        Optional<User> user = userRepository.findByEmail(customerRequest.getUserEmail());
        if (user.isEmpty()) {
            throw new RequestValidationException("User not exist");
        }
        Customer customer = new Customer();
        customer.setCustomerId(customerRequest.getCustomerId());
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setCompany(customerRequest.getCompany());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setEmail(customerRequest.getEmail());
        customer.setCustomerType(CustomerType.fromName(customerRequest.getCustomerType().getName()));
        customer.setCreatedBy(user.get());
        customerRepository.save(customer);
        return CustomerResponse.from("Customer added Successfully", customer);
    }
    @Override
    public GetCustomerResponse getCustomer(String customerId) {
        Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
        if (customer.isEmpty()) {
            throw new RequestValidationException("Customer is not exist");
        }
        return GetCustomerResponse.from(customer.get());
    }

    private Boolean customerExist(String customerId) {
        Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
        return customer.isPresent();
    }
}
