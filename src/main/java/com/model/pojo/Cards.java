package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

// 卡片表pojo
@Repository
public class Cards implements Serializable {
    public final static long serialVersionUID = 1001L;
    private Integer cardId;         // 卡片id
    private String cardNo;          // 卡片编号
    private Double cardMoney;       // 余额
    private Person person;          // 体检人pojo
    private Integer perId;          // 关系表perId的id

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Double getCardMoney() {
        return cardMoney;
    }

    public void setCardMoney(Double cardMoney) {
        this.cardMoney = cardMoney;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cardId=" + cardId +
                ", cardNo='" + cardNo + '\'' +
                ", cardMoney=" + cardMoney +
                ", person=" + person +
                '}';
    }
}
