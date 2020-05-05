package com.dao;

import com.model.pojo.Selects;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface SelectsMapper {
    /**
     * 新增选择项目
     * @param selects   选择表pojo
     * @return
     */
    public boolean insertSelect(Selects selects);

    /**
     * 删除选择的项目
     * @param assoId    套餐id
     * @param itemId    项目id
     * @return
     */
    public boolean deleteSelect(@Param("assoId") Integer assoId, @Param("itemId") Integer itemId);

    /**
     * 查询选择表
     * @param selId 选择表id
     * @return
     */
    public List<Selects> findSelect(@Param("selId") Integer selId);

    /**
     * 清除selects表
     * @return
     */
    public boolean clearSelect();

    /**
     * 查询选择表selects
     * @return
     */
    public List<String> findSelectNames();
}
