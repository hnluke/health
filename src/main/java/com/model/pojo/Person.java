package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

// 体检人表pojo
@Repository
public class Person implements Serializable {
    public final static long serialVersionUID = 1002L;
    private Integer perId;         // 体检人id
    private String perName;        // 姓名
    private String perTele;        // 手机
    private Integer perAge;         // 年龄
    private String perAddr;        // 地址
    private String perBlood;       // 血型
    private String perBorn;        // 出生年月
    private Cards cards;           // 卡片表pojo
    private Integer cardId;         // 关联对象Card的id
    private String perSex;          // 性别



    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPerTele() {
        return perTele;
    }

    public void setPerTele(String perTele) {
        this.perTele = perTele;
    }

    public Integer getPerAge() {
        return perAge;
    }

    public void setPerAge(Integer perAge) {
        this.perAge = perAge;
    }

    public String getPerAddr() {
        return perAddr;
    }

    public void setPerAddr(String perAddr) {
        this.perAddr = perAddr;
    }

    public String getPerBlood() {
        return perBlood;
    }

    public void setPerBlood(String perBlood) {
        this.perBlood = perBlood;
    }

    public String getPerBorn() {
        return perBorn;
    }

    public void setPerBorn(String perBorn) {
        this.perBorn = perBorn;
    }


    public Cards getCards() {
        return cards;
    }

    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getPerSex() {
        return perSex;
    }

    public void setPerSex(String perSex) {
        this.perSex = perSex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "perId=" + perId +
                ", perName='" + perName + '\'' +
                ", perTele='" + perTele + '\'' +
                ", perAge=" + perAge +
                ", perAddr='" + perAddr + '\'' +
                ", perBlood='" + perBlood + '\'' +
                ", perBorn='" + perBorn + '\'' +
                ", cards=" + cards +
                '}';
    }
}
