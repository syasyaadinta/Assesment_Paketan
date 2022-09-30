package com.project.Assesment.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId")
    private Long customerId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Username")
    private Account account;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Level")
    private String level;

    @Column(name = "Balance")
    private BigDecimal balance;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Activity> activityList;

    public void addActivity(Activity newActivity){
        if (activityList == null){
            activityList = new ArrayList<>();
        }
        activityList.add(newActivity);
        newActivity.setCustomer(this);
    }

    public Customer() {}

    public Customer(Long customerId, Account account, String name, String email, String phone, String level, BigDecimal balance, List<Activity> activityList) {
        this.customerId = customerId;
        this.account = account;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.level = level;
        this.balance = balance;
        this.activityList = activityList;
    }


    public Customer(Long customerId, String name, String email, String phone, String level, BigDecimal balance) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.level = level;
        this.balance = balance;
    }

    public Customer(Long customerId, String name, String email, String phone, BigDecimal balance) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    public Customer(Long customerId, String name, String email, String phone) {
        this.customerId = customerId;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", account=" + account +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", level='" + level + '\'' +
                ", balance=" + balance +
                ", activityList=" + activityList +
                '}';
    }
}
