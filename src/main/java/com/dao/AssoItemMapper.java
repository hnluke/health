package com.dao;

import org.apache.ibatis.annotations.Param;

public interface AssoItemMapper {
    /**
     * 插入套餐-项目关联表记录
     * @param assoId
     * @param itemId
     * @return
     */
    public boolean insertAssoItem(@Param("assoId") Integer assoId,
                                  @Param("itemId") Integer itemId);

    /**
     * 删除套餐-项目关联表记录
     * @param asitId
     * @return
     */
    public boolean deleteAssoItem(@Param("asitId") Integer asitId);

    /**
     * 删除指定的套餐
     * @param assoId
     * @return
     */
    public boolean deleteAssoItemByAssoId(@Param("assoId") Integer assoId);
}
