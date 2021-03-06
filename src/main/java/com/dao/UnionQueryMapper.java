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
    public List<Cards> queryCardsPerson(@Param("cardNos") String cardNos);


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
     * 获取打印导检表的数据, 待打印数据, 体检人综合查询
     * @param batchIds  流水表id
     * @param batchCmp  是否已经完成
     * @param batchPay  是否已经缴费
     * @return
     */
    public List<Briefs> queryGuideCheckData(@Param("batchIds") Integer batchIds,
                                            @Param("batchCmp") String batchCmp,
                                            @Param("batchPay") String batchPay);

    /**
     * 获取导检列表, 打印体检报告列表
     * @author luke
     * @param batches       流水表pojo
     * @param person        体检人表pojo
     * @param cards         卡片表pojo
     * @return
     */
    public List<Batches> queryGuideCheckList(@Param("batches") Batches batches,
                                             @Param("person") Person person,
                                             @Param("cards") Cards cards);



//    public List<Briefs> queryCheckReport(Integer batchId);

    /**
     * 查询小结-开单关联表以得到导检信息
     * @param batchId
     * @return
     */
    public List<Briefs> queryBriesfLists(@Param("batchId") Integer batchId);

    /**
     * 查询小结-明细表以得到体检报告信息
     * @param batchNo
     * @return
     */
    public List<Briefs> queryBriesfDetails(@Param("batchNo") String batchNo);


    /**
     * 查询体检总结表数据
     * @param batchNo
     * @return
     */
    public List<Batches> queryBatchSumPerCard(@Param("batchNo") String batchNo);

    /**
     * 查询权限-菜单关联表
     * @param prioName
     * @return
     */
    public List<Priority> queryPrioMenu(@Param("prioName") String prioName);

    /**
     * 关联套餐-项目(多对多)查询
     * @param assoName
     * @return
     */
    public List<Association> queryAssoItem(@Param("assoName") String assoName);

    /**
     * 查询所以没有绑定的卡片
     * @return
     */
    public List<Cards> queryCardsNotPerson();

    /**
     * 查询卡片人员的信息
     * @param batches
     * @param cards
     * @return
     */
    public List<Lists> queryPayBalance(@Param("batches") Batches batches,
                                       @Param("cards") Cards cards);


    /**
     * 查询菜单-权限-用户关联数据
     * @param userName
     * @return
     */
    public List<Menus> queryMenesPrioUsers(@Param("userName") String userName);

    /**
     * 查询用户-科室-权限关联数据
     * @param userId
     * @return
     */
    public List<Users> queryUsersOffPrio(@Param("userId") Integer userId);

    public List<Users> queryUsersOff(@Param("userName") String userName);



}
