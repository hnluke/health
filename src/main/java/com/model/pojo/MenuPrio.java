package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public class MenuPrio implements Serializable {
    public final static long serialVersionUID = 140L;
    private Integer prmeId;
    private Integer prmeMenuId;
    private Integer prmePrioId;
    private Menus menus;
    private Priority priority;

    public Integer getPrmeId() {
        return prmeId;
    }

    public void setPrmeId(Integer prmeId) {
        this.prmeId = prmeId;
    }


    public Integer getPrmeMenuId() {
        return prmeMenuId;
    }

    public void setPrmeMenuId(Integer prmeMenuId) {
        this.prmeMenuId = prmeMenuId;
    }

    public Integer getPrmePrioId() {
        return prmePrioId;
    }

    public void setPrmePrioId(Integer prmePrioId) {
        this.prmePrioId = prmePrioId;
    }

    public Menus getMenus() {
        return menus;
    }

    public void setMenus(Menus menus) {
        this.menus = menus;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "MenuPrio{" +
                "prmeId=" + prmeId +
                ", prmeMenuId=" + prmeMenuId +
                ", prmePrioId=" + prmePrioId +
                ", menus=" + menus +
                ", priority=" + priority +
                '}';
    }
}
