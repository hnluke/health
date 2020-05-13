package com.dao;

//import com.model.pojo.Detail;
import com.model.pojo.Details;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.xml.soap.Detail;
import java.util.List;
@Repository
public interface DetailMapper {
    /**
     * 新增小结明细表记录
     * @param details 明细表对象
     * @return
     */
    public boolean insertDetail(Details details);
//
//    /**
//     * 更新小结明细表
//     * @param result    检查结果
//     * @param prompt    提示
//     * @param id        小结明细表id
//     * @return
//     */
//    public boolean updateDatail(@Param("result") String result,
//                                @Param("prompt") String prompt,
//                                @Param("id") Integer id);
//
//    /**
//     * 查询小结明细表
//     * @param id    依据id来查询小明细表，如果id为0，则查询所有的明细表。
//     * @return
//     */
//    public List<Detail> findDetail(Integer id);
}
