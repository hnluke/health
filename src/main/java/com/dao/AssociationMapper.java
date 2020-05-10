package com.dao;

import com.model.pojo.Association;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AssociationMapper {
    /**
     * 查找套餐
     * @param assoNames 套餐名
     * @return
     */
    public List<Association> findAssociation(@Param("assoNames") String assoNames);

    /**
     * 插入套餐记录
     * @param association   套餐pojo
     * @return
     */
    public boolean insertAssociation(Association association);

    /**
     * 修改套餐表记录
     * @param association
     * @return
     */
    public boolean updateAssociation(Association association);

    public boolean deleteAssociation(@Param("assoId") Integer assoId);
}

