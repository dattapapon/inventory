package com.flagship.dto.response;

import com.flagship.constant.enums.CustomerType;
import com.flagship.model.db.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleCustomer {
  private String customerId;
  private String customerName;
  private String company;
  private String phoneNumber;
  private CustomerType customerType;
  private String supplier;
  private String supplierId;
  private String branch;
  private String branchCode;
  private String address;
  private String binNo;

  public static SingleCustomer from(Customer customer) {
    SingleCustomer.SingleCustomerBuilder builder = SingleCustomer.builder()
            .customerId(customer.getCustomerId())
            .customerName(customer.getCustomerName())
            .company(customer.getCompany())
            .phoneNumber(customer.getPhoneNumber())
            .customerType(customer.getCustomerType())
            .address(customer.getAddress())
            .binNo(customer.getBinNo());

    // Check if supplier is not null before setting its properties
    if (customer.getSupplier() != null) {
      builder = builder.supplier(customer.getSupplier().getSupplierName())
              .supplierId(customer.getSupplier().getSupplierId());
    } else {
      // Set supplier and supplierId to null if customer's supplier is null
      builder = builder.supplier(null).supplierId(null);
    }

    // Check if branch is not null before setting its properties
    if (customer.getBranch() != null) {
      builder = builder.branch(customer.getBranch().getBranchName())
              .branchCode(customer.getBranch().getBranchCode());
    } else {
      // Set branch and branchCode to null if customer's branch is null
      builder = builder.branch(null).branchCode(null);
    }

    return builder.build();
  }

}
