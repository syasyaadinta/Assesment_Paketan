package com.project.Assesment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "History")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HistoryId")
    private Long historyId;

    @Column(name = "ActivityId")
    private Long activityId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ActivityId", insertable = false, updatable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Activity activity;

    @Column(name = "Discount")
    private BigDecimal discount;

    @Column(name = "Bill")
    private BigDecimal bill;

    public History() {}

    public History(Long historyId, Long activityId, BigDecimal discount, BigDecimal bill) {
        this.historyId = historyId;
        this.activityId = activityId;
        this.discount = discount;
        this.bill = bill;
    }

    public History(Long activityId, BigDecimal discount, BigDecimal bill) {
        this.activityId = activityId;
        this.discount = discount;
        this.bill = bill;
    }

    public History(Long historyId, Long activityId) {
        this.historyId = historyId;
        this.activityId = activityId;
    }

    public BigDecimal countDiscount(){
        BigDecimal discount = (this.bill.multiply(this.discount)).divide(new BigDecimal(100));
        System.out.println("[countDiscount : ] "+discount);
        return discount;
    }

    public BigDecimal countBill(){
        BigDecimal bills = this.bill.subtract(countDiscount());
        System.out.println("[countBill : ] "+bills);
        return bills;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Activity getTransaction() {
        return activity;
    }

    public void setTransaction(Activity activity) {
        this.activity = activity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getBill() {
        return bill;
    }

    public void setBill(BigDecimal bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "History{" +
                "historyId=" + historyId +
                ", activityId=" + activityId +
                ", activity=" + activity +
                ", discount=" + discount +
                ", bill=" + bill +
                '}';
    }
}
