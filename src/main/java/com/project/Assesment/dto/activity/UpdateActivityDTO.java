package com.project.Assesment.dto.activity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateActivityDTO {

    private Long activityId;
    private String customerName;
    private Long variantId;
    private LocalDate buyDate;
    private String status;

    public UpdateActivityDTO() {}

    public UpdateActivityDTO(Long activityId, String customerName, Long variantId, LocalDate buyDate, String status) {
        this.activityId = activityId;
        this.customerName = customerName;
        this.variantId = variantId;
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
        return customerName;
    }

    public void setName(String customerName) {
        this.customerName = customerName;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
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
        return "UpdateActivityDTO{" +
                "activityId=" + activityId +
                ", customerName='" + customerName + '\'' +
                ", variantId=" + variantId +
                ", buyDate=" + buyDate +
                ", status='" + status + '\'' +
                '}';
    }
}
