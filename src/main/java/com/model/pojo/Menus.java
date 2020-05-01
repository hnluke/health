package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

// 菜单表pojo
@Repository
public class Menus implements Serializable {
    public final static long serialVersionUID = 117L;
    private Integer menuId;         // 菜单id
    private String menuName;        // 菜单名
    private Priority priority;      // 权限表pojo
    private String menuParent;      // 父菜单id
    private String menuParname;     // 父菜单名称

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(String menuParent) {
        this.menuParent = menuParent;
    }

    public String getMenuParname() {
        return menuParname;
    }

    public void setMenuParname(String menuParname) {
        this.menuParname = menuParname;
    }

    @Override
    public String toString() {
        return "Menus{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", priority=" + priority +
                ", menuParent='" + menuParent + '\'' +
                ", menuParname='" + menuParname + '\'' +
                '}';
    }
}
