package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
// 小结表pojo
public class Briefs implements Serializable {
    public final static long serialVersionUID = 112L;
    private String briefId;         // 小结表id
    private Lists lists;            // 开单表pojo
    private Integer listId;         // 关联开单表id
    private String briefDesc;       // 小结描述
    private String briefName;       // 科室名
    private String briefItemName;       // 项目名称
    private String briefType;       // 项目类别
    private String briefComp;        // 是否已完成
    private String briefUser;       // 检查医生
    private String briefPay;        // 是否缴费
    private String briefBatchNo;    // 流水号
    private String briefCardNo;     // 卡片编号
    private String briefPerson;      // 体检人
    private List<Details> listDetails;  // 小结明细表集合



    public String getBriefId() {
        return briefId;
    }

    public void setBriefId(String briefId) {
        this.briefId = briefId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Lists getLists() {
        return lists;
    }

    public void setLists(Lists lists) {
        this.lists = lists;
    }

    public String getBriefDesc() {
        return briefDesc;
    }

    public void setBriefDesc(String briefDesc) {
        this.briefDesc = briefDesc;
    }

    public String getBriefName() {
        return briefName;
    }

    public void setBriefName(String briefName) {
        this.briefName = briefName;
    }

    public String getBriefItemName() {
        return briefItemName;
    }

    public void setBriefItemName(String briefItemName) {
        this.briefItemName = briefItemName;
    }

    public String getBriefType() {
        return briefType;
    }

    public void setBriefType(String briefType) {
        this.briefType = briefType;
    }

    public String getBriefComp() {
        return briefComp;
    }

    public void setBriefComp(String briefComp) {
        this.briefComp = briefComp;
    }

    public String getBriefUser() {
        return briefUser;
    }

    public void setBriefUser(String briefUser) {
        this.briefUser = briefUser;
    }

    public String getBriefPay() {
        return briefPay;
    }

    public void setBriefPay(String briefPay) {
        this.briefPay = briefPay;
    }

    public String getBriefBatchNo() {
        return briefBatchNo;
    }

    public void setBriefBatchNo(String briefBatchNo) {
        this.briefBatchNo = briefBatchNo;
    }

    public String getBriefCardNo() {
        return briefCardNo;
    }

    public void setBriefCardNo(String briefCardNo) {
        this.briefCardNo = briefCardNo;
    }

    public String getBriefPerson() {
        return briefPerson;
    }

    public void setBriefPerson(String briefPerson) {
        this.briefPerson = briefPerson;
    }

    public List<Details> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<Details> listDetails) {
        this.listDetails = listDetails;
    }

    @Override
    public String toString() {
        return "Briefs{" +
                "briefId='" + briefId + '\'' +
                ", lists=" + lists +
                ", listId=" + listId +
                ", briefDesc='" + briefDesc + '\'' +
                ", briefName='" + briefName + '\'' +
                ", briefItemName='" + briefItemName + '\'' +
                ", briefType='" + briefType + '\'' +
                ", briefComp='" + briefComp + '\'' +
                ", briefUser='" + briefUser + '\'' +
                ", briefPay='" + briefPay + '\'' +
                ", briefBatchNo='" + briefBatchNo + '\'' +
                ", briefCardNo='" + briefCardNo + '\'' +
                ", briefPerson='" + briefPerson + '\'' +
                ", listDetails=" + listDetails +
                '}';
    }
}
