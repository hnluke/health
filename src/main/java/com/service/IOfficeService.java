package com.service;
import com.model.pojo.Office;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOfficeService {

    /**
     * 查找所有科室
     * @return
     */
    List<Office> findAll();


    /**
     * 删除科室
     * @param offId
     * @return
     */
    boolean deleteOffice(Integer offId);

    /**
     * 增加科室
     * @param offName
     * @return
     */
    boolean insertOffice( String offName);

}
