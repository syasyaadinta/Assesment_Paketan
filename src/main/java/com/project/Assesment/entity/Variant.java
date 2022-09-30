package com.project.Assesment.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Variant")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VariantId")
    private Long variantId;

    @Column(name = "VariantName")
    private String variantName;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Period")
    private String period;

    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Activity> activityList;

    public void addActivity(Activity newActivity){
        if (activityList == null){
            activityList = new ArrayList<>();
        }
        activityList.add(newActivity);
        newActivity.setVariant(this);
    }

    public Variant() {}

    public Variant(Long variantId, String variantName, BigDecimal price, String period, String description, List<Activity> activityList) {
        this.variantId = variantId;
        this.variantName = variantName;
        this.price = price;
        this.period = period;
        this.description = description;
        this.activityList = activityList;
    }

    public Variant(Long variantId, String variantName, BigDecimal price, String period, String description) {
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

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public String toString() {
        return "Variant{" +
                "variantId=" + variantId +
                ", variantName='" + variantName + '\'' +
                ", price=" + price +
                ", period='" + period + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
