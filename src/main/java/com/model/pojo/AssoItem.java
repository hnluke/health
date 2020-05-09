package com.model.pojo;

import java.io.Serializable;

// 套项表pojo
public class AssoItem implements Serializable {
    public final static long serialVersionUID = 109L;
    private Integer asitId;     // 关联表id
    private Integer assoId;     // 套餐id
    private Integer itemId;     // 项目id
    private Association association;    // 套餐pojo

    public Integer getAssoId() {
        return assoId;
    }

    public void setAssoId(Integer assoId) {
        this.assoId = assoId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Association getAssociation() {
        return association;
    }

    public void setAssociation(Association association) {
        this.association = association;
    }

    public Integer getAsitId() {
        return asitId;
    }

    public void setAsitId(Integer asitId) {
        this.asitId = asitId;
    }

    @Override
    public String toString() {
        return "AssoItem{" +
                "asitId=" + asitId +
                ", assoId=" + assoId +
                ", itemId=" + itemId +
                ", association=" + association +
                '}';
    }
}