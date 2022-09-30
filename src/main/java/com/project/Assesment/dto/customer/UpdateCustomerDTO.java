package com.project.Assesment.dto.customer;

import java.math.BigDecimal;

public class UpdateCustomerDTO {

    private Long customerId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String level;
    private BigDecimal balance;

    public UpdateCustomerDTO() { }

    public UpdateCustomerDTO(Long customerId, String username, String password, String name, String email, String phone, String level, BigDecimal balance) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.level = level;
        this.balance = balance;
    }

    public UpdateCustomerDTO(Long customerId, String username, String password, String name, String email, String phone) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UpdateCustomerDTO{" +
                "customerId=" + customerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", level='" + level + '\'' +
                ", balance=" + balance +
                '}';
    }
}
