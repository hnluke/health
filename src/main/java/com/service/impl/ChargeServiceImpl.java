package com.service.impl;

import com.dao.*;
import com.model.pojo.Cards;
import com.model.pojo.Lists;
import com.model.pojo.Person;
import com.service.IChargeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChargeServiceImpl implements IChargeService {
    @Resource
    private PersonMapper personMapper;
    @Resource
    private UnionQueryMapper unionQueryMapper;
    @Resource
    private ListsMapper listsMapper;
    @Resource
    private CardsMapper cardsMapper;
    @Resource
    private BriefsMapper briefsMapper;
    @Resource
    private BatchesMapper batchesMapper;

    /**
     * 新建卡
     * @author Luke
     * @param person 用户表pojo
     * @return
     */
    @Override
    public boolean createCards(Person person) {
        return personMapper.insertPerson(person);
    }

    /**
     * 获取当前可用的卡号
     * @author Luke
     * @return
     */
    @Override
    public List<Cards> fetchCardNo() {
        return unionQueryMapper.queryCardsNotPerson();
    }

    public List<Cards> queryCardsPerson(String cardNo) {
        return unionQueryMapper.queryCardsPerson(cardNo);
    }

    /**
     * 查询开单表
     * @param listId
     * @return
     */
    public List<Lists> findLists(Integer listId) {
        return listsMapper.findLists(listId);
    }

    /**
     * 查询卡片表
     * @param cardNo
     * @return
     */
    public List<Cards> findCard(String cardNo) {
        return cardsMapper.findCards(cardNo);
    }

    /**
     * 更新小结表缴费字段
     * @param listId
     * @param briefPay
     * @return
     */
    public boolean updateBriefsPay(Integer listId, String briefPay) {
        return briefsMapper.updateBriefsPay(listId, briefPay);
    }

    public boolean updateBatchesPay(Integer batchId, String batchPay) {
        return batchesMapper.updateBatchesPay(batchId, batchPay);
    }
}
