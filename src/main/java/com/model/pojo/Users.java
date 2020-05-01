package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

// 用户表pojo
@Repository
public class Users implements Serializable {
    public final static long serialVersionUID = 121L;
    private Integer userId;         // 用户id
    private String userName;        // 姓名
    private String pwd;             // 密码
    private Office office;          // 科室表pojo
    private Priority priority;      // 权限表pojo

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", office=" + office +
                ", priority=" + priority +
                '}';
    }
}
