package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

// 套餐表pojo
@Repository
public class Association implements Serializable {
    public final static long serialVersionUID = 110L;
    private Integer assoId;         // 套餐id
    private String assoName;        // 套餐名称
    private AssoItem assoItem;    // 套项表pojo;
    private Double assoPrice;      // 套餐价格

    public Integer getAssoId() {
        return assoId;
    }

    public void setAssoId(Integer assoId) {
        this.assoId = assoId;
    }

    public String getAssoName() {
        return assoName;
    }

    public void setAssoName(String assoName) {
        this.assoName = assoName;
    }

    public AssoItem getAssoItem() {
        return assoItem;
    }

    public void setAssoItem(AssoItem assoItem) {
        this.assoItem = assoItem;
    }

    public Double getAssoPrice() {
        return assoPrice;
    }

    public void setAssoPrice(Double assoPrice) {
        this.assoPrice = assoPrice;
    }
}
