package com.dao;

import com.model.pojo.Priority;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PriorityMapper {

    /**
     * 按照权限名来查询权限表
     * @param prioName
     * @return
     */
    public List<Priority> findPriority(@Param("prioName") String prioName);

    /**
     * 插入权限记录
     * @param priority      权限表pojo
     * @return
     */
    public boolean insertPriority(Priority priority);

    /**
     * 删除权限
     * @param prioId 权限表id
     * @return
     */
    public boolean deletePriority(@Param("prioId") Integer prioId);

    /**
     * 修改权限记录
     * @param priority
     * @return
     */
    public boolean updatePriority(Priority priority);

    /**
     * 返回查询的记录数
     * @param prioName
     * @return
     */
    public Integer findPrioCount(@Param("prioName") String prioName);
}
