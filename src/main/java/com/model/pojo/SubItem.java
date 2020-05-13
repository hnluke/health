package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

// 子项目表pojo
@Repository
public class SubItem implements Serializable {
    public final static long serialVersionUID = 115L;
    private Integer subId;         // 子项目id
    private Integer itemId;         // 项目表id
    private String subName;        // 子项目名称
    private String subCode;        // 子项目编号
    private Double subPrice;       // 价格
    private Item item;              // 项目pojo
    private String subUnit;         // 单位
    private String subRefer;        // 参考值
    private Integer subUpper;        // 健康上限值
    private Integer subLower;        // 健康下限值



    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public Double getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(Double subPrice) {
        this.subPrice = subPrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public String getSubUnit() {
        return subUnit;
    }

    public void setSubUnit(String subUnit) {
        this.subUnit = subUnit;
    }

    public String getSubRefer() {
        return subRefer;
    }

    public void setSubRefer(String subRefer) {
        this.subRefer = subRefer;
    }

    public Integer getSubUpper() {
        return subUpper;
    }

    public void setSubUpper(Integer subUpper) {
        this.subUpper = subUpper;
    }

    public Integer getSubLower() {
        return subLower;
    }

    public void setSubLower(Integer subLower) {
        this.subLower = subLower;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "SubItem{" +
                "subId=" + subId +
                ", itemId=" + itemId +
                ", subName='" + subName + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subPrice=" + subPrice +
                ", item=" + item +
                ", subUnit='" + subUnit + '\'' +
                ", subRefer='" + subRefer + '\'' +
                ", subUpper='" + subUpper + '\'' +
                ", subLower='" + subLower + '\'' +
                '}';
    }
}
