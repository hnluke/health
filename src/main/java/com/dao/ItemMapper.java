package com.dao;

import com.model.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemMapper {
    /**
     * 依据项目名称查找项目表
     * @param itemName  项目名称
     * @return
     */
    public List<Item> findItem(@Param("itemName") String itemName);
}
