package com.dao;

import com.model.pojo.SubItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubItemMapper {
    /**
     * 插入细项目记录
     * @param subItem
     * @return
     */
    public boolean insertSubItem(SubItem subItem);

    /**
     * 查询细项
     * @param subName  细项名称
     * @return
     */
    public List<SubItem> findSubItem(@Param("subName") String subName);

    /**
     * 更新细项
     * @param subItem  细项pojo
     * @return
     */
    public boolean updateSubItem(SubItem subItem);

    /**
     * 删除细项
     * @param subId
     * @return
     */
    public boolean deleteSubItem(@Param("subId") Integer subId);

}
