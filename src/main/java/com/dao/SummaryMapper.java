package com.dao;

import com.model.pojo.Summary;
import org.springframework.stereotype.Repository;
// 体检总结表pojo
@Repository
public interface SummaryMapper {
    /**
     * 新增体检总结记录
     * @param summary
     * @return
     */
    public boolean insertSummary(Summary summary);

}
