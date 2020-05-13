package com.dao;

import com.model.pojo.Batches;
import com.model.pojo.Lists;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ListsMapper {
    /**
     *  向开单表插入数据
     * @param lists 开单表对象
     * @return
     */
    public boolean insertLists(Lists lists);
//
//    /**
//     * 更新开单表
//     * @param lists
//     * @return
//     */
//    public boolean updateLists(Lists lists);
//
//    /**
//     * 删除开单表记录
//     * @param id 依据开单表id删除对应的开单记录。
//     * @return
//     */
//    public boolean deleteLists(Integer id);
//
//    /**
//     * 依据开单表id来查询开单记录，如果id为0，则查询所有的开单记录
//     * @param id
//     * @return
//     */
    public List<Lists> findLists(@Param("listId") Integer listId);
}
