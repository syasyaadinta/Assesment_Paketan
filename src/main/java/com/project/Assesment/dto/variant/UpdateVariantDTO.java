package com.project.Assesment.dto.variant;

import com.project.Assesment.entity.History;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

public class UpdateVariantDTO {

    private Long variantId;
    private String variantName;
    private BigDecimal price;
    private String period;
    private String description;

    public UpdateVariantDTO() {}

    public UpdateVariantDTO(Long variantId, String variantName, BigDecimal price, String period, String description) {
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
}
