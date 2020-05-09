package com.dao;

import com.model.pojo.MenuPrio;
import com.model.pojo.Priority;
import org.apache.ibatis.annotations.Param;

public interface MenuPrioMapper {

    /**
     * 向菜单-权限关联表插入记录
     * @return
     */
    public boolean insertMenuPrio(@Param("prmePrioId") Integer prmePrioId,
                                  @Param("prmeMenuId") Integer prmeMenuId);

    /**
     * 删除菜单权限关联表记录
     * @param prmeId    关联表id
     * @return
     */
    public boolean deleteMenuPrio(@Param("prmeId") Integer prmeId);
}
