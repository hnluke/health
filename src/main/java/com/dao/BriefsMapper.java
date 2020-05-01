package com.dao;

import com.model.pojo.Briefs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BriefsMapper {
    /**
     * 新增小结表记录
     * @param briefs 小结表记录对象
     * @return
     */
    public boolean insertBriefs(Briefs briefs);

    /**
     * 更新小结表
     * @param desc  小结描述
     * @param id    小结表id
     * @return
     */
    public boolean updateBriefs(@Param("desc") String desc, @Param("id") Integer id);

    /**
     * 依据小结表id来查询小结表，如果id=0则查询全部的小结记录
     * @param id
     * @return 小结表对象
     */
    public List<Briefs> findBriefs(Integer id);
}
