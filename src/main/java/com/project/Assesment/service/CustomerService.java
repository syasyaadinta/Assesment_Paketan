package com.project.Assesment.service;

import com.project.Assesment.dto.account.AccountRegisterDTO;
import com.project.Assesment.dto.customer.UpdateCustomerDTO;
import com.project.Assesment.entity.Customer;

public interface CustomerService {
    Customer findCustomer(String username);

    UpdateCustomerDTO getUpdateCustomer(String username);

    Long registerCustomerAccount(AccountRegisterDTO dto);

    Customer getCustomer(Long respondId);

    Long updateCustomer(UpdateCustomerDTO dto);

    Long updateCustomerBalance(UpdateCustomerDTO dto, String username);
}
