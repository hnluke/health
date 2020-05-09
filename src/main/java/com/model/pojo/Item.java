package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

// 项目表pojo
@Repository
public class Item implements Serializable {
    public final static long serialVersionUID = 106L;
    private Integer itemId;             // 项目id
    private Integer assoId;             // 关联套餐表id
    private Integer itemTypeId;         // 关联类别表id
    private Office office;              // 科室表pojo
    private String itemName;            // 项目名称
    private String itemCode;            // 项目编号
    private Double itemPrice;           // 价格
    //private Association association;    // 套餐表pojo
    private List<SubItem> subItemList;       // 子项目表pojo
    private List<Association> listAsso;   // 套餐表pojo
    private ItemType itemType;          // 项目类别pojo

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

    public List<Association> getListAsso() {
        return listAsso;
    }

    public void setListAsso(List<Association> listAsso) {
        this.listAsso = listAsso;
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

    public Integer getAssoId() {
        return assoId;
    }

    public void setAssoId(Integer assoId) {
        this.assoId = assoId;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public List<SubItem> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(List<SubItem> subItemList) {
        this.subItemList = subItemList;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", assoId=" + assoId +
                ", itemTypeId=" + itemTypeId +
                ", office=" + office +
                ", itemName='" + itemName + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", itemPrice=" + itemPrice +
                ", listAsso=" + listAsso +
                ", itemType=" + itemType +
                '}';
    }
}
