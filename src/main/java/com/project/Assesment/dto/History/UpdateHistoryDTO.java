package com.project.Assesment.dto.History;

import java.math.BigDecimal;

public class UpdateHistoryDTO {

    private Long historyId;
    private Long activityId;
    private String variantName;
    private String customerName;
    private BigDecimal discount;
    private BigDecimal totalBill;

    public UpdateHistoryDTO(){}

    public UpdateHistoryDTO(Long historyId, Long activityId, String variantName, String customerName, BigDecimal discount, BigDecimal totalBill) {
        this.historyId = historyId;
        this.activityId = activityId;
        this.variantName = variantName;
        this.customerName = customerName;
        this.discount = discount;
        this.totalBill = totalBill;
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

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public String toString() {
        return "UpdateHistoryDTO{" +
                "historyId=" + historyId +
                ", activityId=" + activityId +
                ", variantName='" + variantName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", discount=" + discount +
                ", totalBill=" + totalBill +
                '}';
    }
}
