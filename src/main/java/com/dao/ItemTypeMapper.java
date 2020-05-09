package com.dao;

import com.model.pojo.ItemType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemTypeMapper {
    /**
     * 插入类型记录
     * @param typeName  类型名称
     * @return
     */
    public boolean insertItemType(@Param("typeName") String typeName);

    /**
     * 查询项目类别表
     * @param typeName
     * @return
     */
    public List<ItemType> findItemType(@Param("typeName") String typeName);

}
