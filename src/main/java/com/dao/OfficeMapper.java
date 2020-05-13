package com.dao;

import com.model.pojo.Office;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OfficeMapper {

    /**
     * 插入科室记录
     * @param offName
     * @return
     */
    public boolean insertOffice(@Param("offName") String offName);

    /**
     * 查询科室记录
     * @param offName
     * @return
     */
    public List<Office> findOffice(@Param("offName") String offName);
}
