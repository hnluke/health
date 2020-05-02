package com.model.pojo;

import java.io.Serializable;

public class Selects implements Serializable {
    public final static long serialVersionUID = 130L;
    private Integer selId;          // 选择表id
    private Integer selAssoId;      // 套餐表id
    private Integer selItemId;      // 项目表id
    private String selItemName;     //  项目名称
    private String selIType;        //  项目类别
    private String selOff;          //  科室
    private Double selPrice;        //  价格

    public Integer getSelId() {
        return selId;
    }

    public void setSelId(Integer selId) {
        this.selId = selId;
    }

    public Integer getSelAssoId() {
        return selAssoId;
    }

    public void setSelAssoId(Integer selAssoId) {
        this.selAssoId = selAssoId;
    }

    public Integer getSelItemId() {
        return selItemId;
    }

    public void setSelItemId(Integer selItemId) {
        this.selItemId = selItemId;
    }

    public String getSelItemName() {
        return selItemName;
    }

    public void setSelItemName(String selItemName) {
        this.selItemName = selItemName;
    }

    public String getSelIType() {
        return selIType;
    }

    public void setSelIType(String selIType) {
        this.selIType = selIType;
    }

    public String getSelOff() {
        return selOff;
    }

    public void setSelOff(String selOff) {
        this.selOff = selOff;
    }

    public Double getSelPrice() {
        return selPrice;
    }

    public void setSelPrice(Double selPrice) {
        this.selPrice = selPrice;
    }

    @Override
    public String toString() {
        return "Selects{" +
                "selId=" + selId +
                ", selAssoId=" + selAssoId +
                ", selItemId=" + selItemId +
                ", selItemName='" + selItemName + '\'' +
                ", selIType='" + selIType + '\'' +
                ", selOff='" + selOff + '\'' +
                ", selPrice=" + selPrice +
                '}';
    }
}
