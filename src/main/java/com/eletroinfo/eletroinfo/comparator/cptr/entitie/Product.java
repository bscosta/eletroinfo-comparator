package com.eletroinfo.eletroinfo.comparator.cptr.entitie;

import com.eletroinfo.eletroinfo.comparator.entitie.BaseEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product", schema = "cptr")
@DynamicUpdate
public class Product extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "unit_measure")
    private String unitMeasure;

    @Column(name = "measured_quantity")
    private Integer measuredQuantity;

    private Integer barcode;

    public Boolean isNovo() {
        return this.id == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public Integer getMeasuredQuantity() {
        return measuredQuantity;
    }

    public void setMeasuredQuantity(Integer measuredQuantity) {
        this.measuredQuantity = measuredQuantity;
    }

    public Integer getBarcode() {
        return barcode;
    }

    public void setBarcode(Integer barcode) {
        this.barcode = barcode;
    }
}
