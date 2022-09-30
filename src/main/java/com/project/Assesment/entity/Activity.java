package com.project.Assesment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ActivityId")
    private Long activityId;

    @Column(name = "CustomerId")
    private Long customerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", insertable = false, updatable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Customer customer;

    @Column(name = "VariantId")
    private Long variantId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VariantId", insertable = false, updatable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Variant variant;

    @Column(name = "Bill")
    private BigDecimal bill;

    @Column(name = "BuyDate")
    private LocalDate buyDate;

    @Column(name = "Status")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<History> historyList;

    public void addHistory(History newHistory){
        if (historyList == null){
            historyList = new ArrayList<>();
        }
        historyList.add(newHistory);
        newHistory.setActivity(this);
    }

    public Activity() {}

    public Activity(Long activityId, Long customerId, Customer customer, BigDecimal bill, LocalDate buyDate, String status, List<History> historyList) {
        this.activityId = activityId;
        this.customerId = customerId;
        this.customer = customer;
        this.bill = bill;
        this.buyDate = buyDate;
        this.status = status;
        this.historyList = historyList;
    }

    public Activity(Long activityId, Long customerId, Long variantId, BigDecimal bill, LocalDate buyDate, String status) {
        this.activityId = activityId;
        this.customerId = customerId;
        this.variantId = variantId;
        this.bill = bill;
        this.buyDate = buyDate;
        this.status = status;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }


    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", customerId=" + customerId +
                ", variantId=" + variantId +
                ", bill=" + bill +
                ", buyDate=" + buyDate +
                ", status='" + status + '\'' +
                '}';
    }
}
