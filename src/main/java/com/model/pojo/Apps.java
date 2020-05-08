package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public class Apps implements Serializable {
    public final static long serialVersionUID = 133L;

    private String assoName;
    private String itemName;
    private String chk;
    private Integer assoId;
    private Integer itemId;

    public String getAssoName() {
        return assoName;
    }

    public void setAssoName(String assoName) {
        this.assoName = assoName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getChk() {
        return chk;
    }

    public void setChk(String chk) {
        this.chk = chk;
    }

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

    @Override
    public String toString() {
        return "Apps{" +
                "assoName='" + assoName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", chk='" + chk + '\'' +
                ", assoId=" + assoId +
                ", itemId=" + itemId +
                '}';
    }
}
