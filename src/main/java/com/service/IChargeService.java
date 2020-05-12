package com.service;

import com.model.pojo.Cards;
import com.model.pojo.Lists;
import com.model.pojo.Person;

import java.util.List;

public interface IChargeService {
    /**
     * 新建用户卡绑定
     * @param person 用户表pojo
     * @return
     */
    public boolean createCards(Person person);

    /**
     * 获取当前可用的卡号
     * @return
     */
    public List<Cards> fetchCardNo();

    /**
     * 获取所有已经建卡的用户信息
     * @param cardNo
     * @return
     */
    public List<Cards> queryCardsPerson(String cardNo);

    /**
     * 查询开单表
     * @param listId
     * @return
     */
    public List<Lists> findLists(Integer listId);

    /**
     * 查询卡片表
     * @param cardNo
     * @return
     */
    public List<Cards> findCard(String cardNo);

    /**
     * 更新小结表的缴费字段
     * @param listId
     * @param briefPay
     * @return
     */
    public boolean updateBriefsPay(Integer listId, String briefPay);

    /**
     * 更新流水表的缴费字段
     * @param batchId
     * @param batchPay
     * @return
     */
    public boolean updateBatchesPay(Integer batchId, String batchPay);
}
