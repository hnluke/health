package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

// 小结明细表pojo
@Repository
public class Details implements Serializable {
    public final static long serialVersionUID = 114L;
    private Integer detId;          // 小结明细表id
    private Briefs briefs;          // 小结表pojo
    private SubItem subItem;        // 子项目pojo
    private String detItemName;     // 子项目名称
    private String detResult;       // 结果
    private String detPrompt;       // 提示
    private String detCmp;          // 是否已完成
    private String detBmp;          // 影像路径
    private String detUnit;         // 单位
    private String detRefer;        // 参考值
    private String detUpper;        // 上限值
    private String detLower;        // 下限值


    public Integer getDetId() {
        return detId;
    }

    public void setDetId(Integer detId) {
        this.detId = detId;
    }

    public Briefs getBriefs() {
        return briefs;
    }

    public void setBriefs(Briefs briefs) {
        this.briefs = briefs;
    }

    public SubItem getSubItem() {
        return subItem;
    }

    public void setSubItem(SubItem subItem) {
        this.subItem = subItem;
    }

    public String getDetItemName() {
        return detItemName;
    }

    public void setDetItemName(String detItemName) {
        this.detItemName = detItemName;
    }

    public String getDetResult() {
        return detResult;
    }

    public void setDetResult(String detResult) {
        this.detResult = detResult;
    }

    public String getDetPrompt() {
        return detPrompt;
    }

    public void setDetPrompt(String detPrompt) {
        this.detPrompt = detPrompt;
    }

    public String getDetCmp() {
        return detCmp;
    }

    public void setDetCmp(String detCmp) {
        this.detCmp = detCmp;
    }

    public String getDetBmp() {
        return detBmp;
    }

    public void setDetBmp(String detBmp) {
        this.detBmp = detBmp;
    }

    public String getDetUnit() {
        return detUnit;
    }

    public void setDetUnit(String detUnit) {
        this.detUnit = detUnit;
    }

    public String getDetRefer() {
        return detRefer;
    }

    public void setDetRefer(String detRefer) {
        this.detRefer = detRefer;
    }

    public String getDetUpper() {
        return detUpper;
    }

    public void setDetUpper(String detUpper) {
        this.detUpper = detUpper;
    }

    public String getDetLower() {
        return detLower;
    }

    public void setDetLower(String detLower) {
        this.detLower = detLower;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "detId=" + detId +
                ", briefs=" + briefs +
                ", subItem=" + subItem +
                ", detItemName='" + detItemName + '\'' +
                ", detResult='" + detResult + '\'' +
                ", detPrompt='" + detPrompt + '\'' +
                ", detCmp='" + detCmp + '\'' +
                ", detBmp='" + detBmp + '\'' +
                ", detUnit='" + detUnit + '\'' +
                ", detRefer='" + detRefer + '\'' +
                ", detUpper='" + detUpper + '\'' +
                ", detLower='" + detLower + '\'' +
                '}';
    }
}
