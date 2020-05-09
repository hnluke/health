package com.model.pojo;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

// 菜单表pojo
@Repository
public class Menus implements Serializable {
    public final static long serialVersionUID = 117L;
    private Integer menuId;             // 菜单id
    private String menuPath;            // 访问路径
    private Integer menuResId;          // 菜单id
    private Integer menuParId;          // 父菜单id
    private Integer menuGrpId;          // 组id
    private String menuName;            // 菜单名
    private MenuPrio menuPrio;          // 菜单权限

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public Integer getMenuResId() {
        return menuResId;
    }

    public void setMenuResId(Integer menuResId) {
        this.menuResId = menuResId;
    }

    public Integer getMenuParId() {
        return menuParId;
    }

    public void setMenuParId(Integer menuParId) {
        this.menuParId = menuParId;
    }

    public Integer getMenuGrpId() {
        return menuGrpId;
    }

    public void setMenuGrpId(Integer menuGrpId) {
        this.menuGrpId = menuGrpId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public MenuPrio getMenuPrio() {
        return menuPrio;
    }

    public void setMenuPrio(MenuPrio menuPrio) {
        this.menuPrio = menuPrio;
    }

    @Override
    public String toString() {
        return "Menus{" +
                "menuId=" + menuId +
                ", menuPath='" + menuPath + '\'' +
                ", menuResId=" + menuResId +
                ", menuParId=" + menuParId +
                ", menuGrpId=" + menuGrpId +
                ", menuName='" + menuName + '\'' +
                ", menuPrio=" + menuPrio +
                '}';
    }
}
