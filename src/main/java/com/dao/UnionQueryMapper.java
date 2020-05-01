package com.dao;

import com.model.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UnionQueryMapper {
    /**
     * 依据卡片编号来对【卡片表-检查人表】关联查询
     * @author Luke
     * @param cardNos   卡片编号，如果是""或null ,则查询全部的记录
     * @return
     */
    public List<Cards> queryCardsPerson(String cardNos);

    /**
     * 获取需要插入小结表的数据
     * @author Luke
     * @param assoName  套餐名
     * @param itemName
     * @return 返回Item对象列表
     */
    public List<Item> queryBriefData(@Param("assoName") String assoName,
                                           @Param("itemName") String itemName);

    /**
     * 获取打印导检表的数据
     * @param batchIds   流水编号
     * @return
     */
    public List<Briefs> queryGuideCheckData(Integer batchIds);

    /**
     * 获取导检列表, 打印体检报告列表
     * @author
     * @param cardNo        卡片编号
     * @param prts          是否打印导检表
     * @param prtRpts       是否打印体检报表
     * @param batchPays     是否已缴费
     * @param batchCmps     是否已完成
     * @return
     */
    public List<Batches> queryGuideCheckList(@Param("cardNo") String cardNo,
                                             @Param("prts") String prts,
                                             @Param("prtRpts") String prtRpts,
                                             @Param("batchPays") String batchPays,
                                             @Param("batchCmps") String batchCmps);

    public List<Briefs> queryCheckReport(Integer batchId);



}
