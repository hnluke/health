package com.model.pojo;

import java.io.Serializable;

// 套项表pojo
public class AssoItem implements Serializable {
    public final static long serialVersionUID = 109L;
    private Integer assoId;     // 套餐id
    private Integer itemId;     // 项目id

    public Integer getAssoId() {
        return assoId;
    }

    public void setAssoId(Integer assoId) {
        this.assoId = assoId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}