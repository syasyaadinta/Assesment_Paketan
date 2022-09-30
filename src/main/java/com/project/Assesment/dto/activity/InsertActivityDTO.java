package com.project.Assesment.dto.activity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InsertActivityDTO {

    private Long activityId;
    private String name;
    private Long variantId;
    private LocalDate buyDate;
    private String status;

    public InsertActivityDTO() {}

    public InsertActivityDTO(Long activityId, String name, Long variantId, LocalDate buyDate, String status) {
        this.activityId = activityId;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "InsertActivityDTO{" +
                "activityId=" + activityId +
                ", name='" + name + '\'' +
                ", variantId=" + variantId +
                ", buyDate=" + buyDate +
                ", status='" + status + '\'' +
                '}';
    }
}
