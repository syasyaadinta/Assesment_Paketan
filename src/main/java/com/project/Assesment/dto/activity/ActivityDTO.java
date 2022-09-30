package com.project.Assesment.dto.activity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ActivityDTO {

    private Long activityId;
    private String name;
    private BigDecimal bill;
    private LocalDate buyDate;
    private String status;

    public ActivityDTO() {}

    public ActivityDTO(Long activityId, String name, BigDecimal bill, LocalDate buyDate, String status) {
        this.activityId = activityId;
        this.name = name;
        this.bill = bill;
        this.buyDate = buyDate;
        this.status = status;
    }

    public ActivityDTO(Long activityId, BigDecimal bill, LocalDate buyDate, String status) {
        this.activityId = activityId;
        this.bill = bill;
        this.buyDate = buyDate;
        this.status = status;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "ActivityDTO{" +
                "activityId=" + activityId +
                ", name='" + name + '\'' +
                ", bill=" + bill +
                ", buyDate=" + buyDate +
                ", status='" + status + '\'' +
                '}';
    }
}
