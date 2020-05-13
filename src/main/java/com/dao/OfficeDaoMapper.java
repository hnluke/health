package com.dao;

import com.model.pojo.Office;
import com.model.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeDaoMapper {
    /**
     * 按照科室id查找科室
     * @param offId
     * @return
     */
    List<Office> findOfficesById(@Param("offId")Integer offId);

    /**
     * 删除科室
     * @param offId
     * @return
     */
    boolean deleteOffice(@Param("offId") Integer offId);


    /**
     * 增加科室
     * @param offName
     * @return
     */
     boolean insertOffice(@Param("offName") String offName);



}
