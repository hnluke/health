package com.dao;

import com.model.pojo.Cards;
import com.model.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 卡片pojo
@Repository
public interface CardsMapper{
    /**
     * 插入用户记录
     * @param cards 用户pojo
     */
    public boolean insertCards(Cards cards);
//
    /**
     * 更新用户记录
     * @param cards 用户表pojo
     */
    public boolean updateCards(Cards cards);

    /**
     * 删除满足id的用户
     * @param id    用户id
     */
    public boolean deleteUsers(Integer id);

    /**
     * 依据卡片编号查询卡, 如果id为0，则查询所有的用户信息
     * @param cardNo
     * @return
     */
    public List<Cards> findCards(@Param("cardNo") String cardNo);
}

