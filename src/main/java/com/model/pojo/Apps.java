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
    private String param;       // 参数: "体检缴费","体检退费"
    private String cardNo;      // 卡片编号

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

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String toString() {
        return "Apps{" +
                "assoName='" + assoName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", chk='" + chk + '\'' +
                ", assoId=" + assoId +
                ", itemId=" + itemId +
                ", param='" + param + '\'' +
                ", cardNo='" + cardNo + '\'' +
                '}';
    }
}
