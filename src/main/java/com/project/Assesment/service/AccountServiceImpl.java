package com.project.Assesment.service;

import com.project.Assesment.configuration.ApplicationUserDetails;
import com.project.Assesment.dao.AccountRepository;
import com.project.Assesment.dao.CustomerRepository;
import com.project.Assesment.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> nullableEntity = accountRepository.findById(username);
        Account account = nullableEntity.get();
        return new ApplicationUserDetails(account);
    }

    @Override
    public String getAccountRole(String username) {
        Optional<Account> nullableEntity = accountRepository.findById(username);
        Account account = null;
        if (nullableEntity.isPresent()){
            account = nullableEntity.get();
        }
        return account.getRole();
    }


}
