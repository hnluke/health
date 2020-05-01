package com.model.pojo;

import java.io.Serializable;
import java.util.Date;

// 体检报告表pojo
public class CheckReport implements Serializable {
    public final static long serialVersionUID = 113L;
    private Integer chkId;          // 体检报告id
    private String chkItemName;     // 项目名称
    private String chkSub;          // 子项目名称
    private String chkScope;        // 参考范围
    private String chkUnit;         // 单位
    private Batches batches;        // 流水表pojo
    private String chkResult;      // 检查结果
    private String chkPrompt;      // 提示
    private String chkSum;         // 小结
    private String chkBmp;         // 影像路径
    private String chkUser;        // 检查医生
    private Date chkDate;          // 报告日期
    private Office chkOff;          // 科室pojo

    public Integer getChkId() {
        return chkId;
    }

    public void setChkId(Integer chkId) {
        this.chkId = chkId;
    }

    public String getChkItemName() {
        return chkItemName;
    }

    public void setChkItemName(String chkItemName) {
        this.chkItemName = chkItemName;
    }

    public String getChkSub() {
        return chkSub;
    }

    public void setChkSub(String chkSub) {
        this.chkSub = chkSub;
    }

    public String getChkScope() {
        return chkScope;
    }

    public void setChkScope(String chkScope) {
        this.chkScope = chkScope;
    }

    public String getChkUnit() {
        return chkUnit;
    }

    public void setChkUnit(String chkUnit) {
        this.chkUnit = chkUnit;
    }

    public Batches getBatches() {
        return batches;
    }

    public void setBatches(Batches batches) {
        this.batches = batches;
    }

    public String getChkResult() {
        return chkResult;
    }

    public void setChkResult(String chkResult) {
        this.chkResult = chkResult;
    }

    public String getChkPrompt() {
        return chkPrompt;
    }

    public void setChkPrompt(String chkPrompt) {
        this.chkPrompt = chkPrompt;
    }

    public String getChkSum() {
        return chkSum;
    }

    public void setChkSum(String chkSum) {
        this.chkSum = chkSum;
    }

    public String getChkBmp() {
        return chkBmp;
    }

    public void setChkBmp(String chkBmp) {
        this.chkBmp = chkBmp;
    }

    public String getChkUser() {
        return chkUser;
    }

    public void setChkUser(String chkUser) {
        this.chkUser = chkUser;
    }

    public Date getChkDate() {
        return chkDate;
    }

    public void setChkDate(Date chkDate) {
        this.chkDate = chkDate;
    }

    public Office getChkOff() {
        return chkOff;
    }

    public void setChkOff(Office chkOff) {
        this.chkOff = chkOff;
    }

    @Override
    public String toString() {
        return "CheckReport{" +
                "chkId=" + chkId +
                ", chkItemName='" + chkItemName + '\'' +
                ", chkSub='" + chkSub + '\'' +
                ", chkScope='" + chkScope + '\'' +
                ", chkUnit='" + chkUnit + '\'' +
                ", batches=" + batches +
                ", chkResult='" + chkResult + '\'' +
                ", chkPrompt='" + chkPrompt + '\'' +
                ", chkSum='" + chkSum + '\'' +
                ", chkBmp='" + chkBmp + '\'' +
                ", chkUser='" + chkUser + '\'' +
                ", chkDate=" + chkDate +
                ", chkOff=" + chkOff +
                '}';
    }
}
