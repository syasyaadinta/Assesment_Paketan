package com.project.Assesment.dto.variant;

import java.math.BigDecimal;

public class VariantGridDTO {

    private Long variantId;
    private String variantName;
    private BigDecimal price;
    private String period;
    private String description;

    public VariantGridDTO() {}

    public VariantGridDTO(Long variantId, String variantName, BigDecimal price, String period, String description) {
        this.variantId = variantId;
        this.variantName = variantName;
        this.price = price;
        this.period = period;
        this.description = description;
    }

    public Long getVariantId() {
        return variantId;
    }

    public void setVariantId(Long variantId) {
        this.variantId = variantId;
    }

    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "VariantGridDTO{" +
                "variantId=" + variantId +
                ", variantName='" + variantName + '\'' +
                ", price=" + price +
                ", period='" + period + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
