package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

// 项目表pojo
@Repository
public class Item implements Serializable {
    public final static long serialVersionUID = 106L;
    private Integer itemId;             // 项目id
    private Office office;              // 科室表pojo
    private Integer offId;              // 科室表id
    private String itemName;            // 项目名称
    private String itemCode;            // 项目编号
    private Double itemPrice;           // 价格
    private List<SubItem> subItemList;       // 子项目表pojo
    private List<AssoItem> assoItems;       // 关联表集合
    private ItemType itemType;          // 项目类别pojo
    private Integer itemTypeId;         // 项目类别表id

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }


    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public List<AssoItem> getAssoItems() {
        return assoItems;
    }

    public void setAssoItems(List<AssoItem> assoItems) {
        this.assoItems = assoItems;
    }

    public List<SubItem> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(List<SubItem> subItemList) {
        this.subItemList = subItemList;
    }

    public Integer getOffId() {
        return offId;
    }

    public void setOffId(Integer offId) {
        this.offId = offId;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", office=" + office +
                ", offId=" + offId +
                ", itemName='" + itemName + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemPrice=" + itemPrice +
                ", subItemList=" + subItemList +
                ", assoItems=" + assoItems +
                ", itemType=" + itemType +
                ", itemTypeId=" + itemTypeId +
                '}';
    }
}
