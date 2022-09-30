package com.project.Assesment.service;

import com.project.Assesment.dao.CustomerRepository;
import com.project.Assesment.dto.account.AccountRegisterDTO;
import com.project.Assesment.dto.customer.UpdateCustomerDTO;
import com.project.Assesment.entity.Account;
import com.project.Assesment.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements  CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Customer findCustomer(String username) {
        Customer customer = customerRepository.findByUsername(username);
        System.out.println("Find Customer By Username : "+customer);
        return customer;
    }

    @Override
    public UpdateCustomerDTO getUpdateCustomer(String username) {
        Customer customer = customerRepository.findByUsername(username);
        UpdateCustomerDTO updateCustomer = new UpdateCustomerDTO(
                customer.getCustomerId(),
                customer.getAccount().getUsername(),
                customer.getAccount().getPassword(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getLevel(),
                customer.getBalance()
        );
        return updateCustomer;
    }

    @Override
    public Long registerCustomerAccount(AccountRegisterDTO dto) {
        // Get password encoder
        String hashPassword = passwordEncoder.encode(dto.getPassword());

        // Add Customer
        Customer newCustomer = new Customer(
                dto.getCustomerId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhone(),
                "Member",
                new BigDecimal(0)
        );

        // Add Account
        Account newAccount = new Account(
                dto.getUsername(),
                hashPassword,
                "Customer"
        );
        newCustomer.setAccount(newAccount);
        Customer respond = customerRepository.save(newCustomer);
        return respond.getCustomerId();
    }

    @Override
    public Customer getCustomer(Long respondId) {
        Optional<Customer> findCust = customerRepository.findById(respondId);
        Customer cust = null;
        if (findCust.isPresent()){
            cust = findCust.get();
        }
        return cust;
    }

    @Override
    public Long updateCustomer(UpdateCustomerDTO dto) {
        Optional<Customer> findCust = customerRepository.findById(dto.getCustomerId());
        Customer cust = null;
        if (findCust.isPresent()){
            cust = findCust.get();
        }
        Customer updateCust = new Customer(
                dto.getCustomerId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPhone()
        );
        Account updateAcc = new Account(
                cust.getAccount().getUsername(),
                cust.getAccount().getPassword(),
                cust.getAccount().getRole()
        );
        updateCust.setAccount(updateAcc);
        updateCust.setLevel(cust.getLevel());
        updateCust.setBalance(cust.getBalance());
        updateCust.setActivityList(cust.getActivityList());

        customerRepository.save(updateCust);
        return updateCust.getCustomerId();
    }

    @Override
    public Long updateCustomerBalance(UpdateCustomerDTO dto, String username) {
        Optional<Customer> findCust = customerRepository.findById(dto.getCustomerId());

        Customer cust = null;
        if (findCust.isPresent()){
            cust = findCust.get();
        }
        BigDecimal balance = cust.getBalance().add(dto.getBalance());
        System.out.println("[After add balance : ] "+balance);

        Account updateAcc = new Account(
                cust.getAccount().getUsername(),
                cust.getAccount().getPassword(),
                cust.getAccount().getRole()
        );
        Customer updateCust = new Customer(
                dto.getCustomerId(),
                updateAcc,
                cust.getName(),
                cust.getEmail(),
                cust.getPhone(),
                cust.getLevel(),
                balance,
                cust.getActivityList()
        );

        customerRepository.save(updateCust);
        return updateCust.getCustomerId();
    }
}
