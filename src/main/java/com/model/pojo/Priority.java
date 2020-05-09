package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

// 权限表pojo
@Repository
public class Priority implements Serializable {
    public final static long serialVersionUID = 118L;
    private Integer prioId;        // 权限表id
    private String prioName;       // 权限名
    private String prioDesc;        // 权限描述
    private List<MenuPrio> menuPrioList;  // 菜单pojo集合;

    public Integer getPrioId() {
        return prioId;
    }

    public void setPrioId(Integer prioId) {
        this.prioId = prioId;
    }

    public String getPrioName() {
        return prioName;
    }

    public void setPrioName(String prioName) {
        this.prioName = prioName;
    }

    public String getPrioDesc() {
        return prioDesc;
    }

    public void setPrioDesc(String prioDesc) {
        this.prioDesc = prioDesc;
    }

    public List<MenuPrio> getMenuPrioList() {
        return menuPrioList;
    }

    public void setMenuPrioList(List<MenuPrio> menuPrioList) {
        this.menuPrioList = menuPrioList;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "prioId=" + prioId +
                ", prioName='" + prioName + '\'' +
                ", prioDesc='" + prioDesc + '\'' +
                ", menuPrioList=" + menuPrioList +
                '}';
    }
}
