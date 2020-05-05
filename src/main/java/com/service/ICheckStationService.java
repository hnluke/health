package com.service;

import com.model.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICheckStationService {
    /**
     *
     * 开单
     * @author Luke
     * @param cardNos   卡片编号
     * @param itemNames  要开单的项目名称
     * @return
     */
    public String createList(String cardNos, List<String> itemNames);

    /**
     * 打印导检表
     * @author Luke
     * @param batchId 流水表id
     */
//    public void printGuideCheck(Integer batchId);

    /**
     * 显示导检表打印列表, 体检报告列表
     * @param batches   流水表pojo
     * @param person    体检人表pojo
     * @param cards     卡片表pojo
     * @return
     */
    public List<Batches> showGuideGheck(Batches batches, Person person, Cards cards);

    /**
     * 依据套餐名来查询套餐表
     * @param assoName  套餐名 如果为null或空，则查询套餐表所有的数据
     * @return
     */
    public List<Association> showAllAsso(String assoName);

    /**
     * 依据项目名来查询项目表
     * @param itemName  项目名称，如果为null或空，则查询项目表中所有的数据
     * @return
     */
    public List<Item> showAllItem(String itemName);

    /**
     * 将满足套餐名或项目名的项目插入到selects表中。
     * @param assoName
     * @param itemName
     * @return
     */
    public List<Selects> insertSelect(String assoName, String itemName);

    /**
     * 删除选择记录
     * @param assoId
     * @param itemId
     * @return
     */
    public List<Selects> deleteSelect(Integer assoId, Integer itemId);

    /**
     * 清除选择表selects数据
     * @return
     */
    public List<Selects> clearSelect();

    /**
     * 获取导检表数据
     * @param batchId   流水表id
     * @return
     */
    public List<Briefs> showGuideData(Integer batchId);

    /**
     * 获取体检报告数据
     * @param batchNo   流水号
     * @return
     */
    public List<Briefs> showReportData(String batchNo);

    /**
     * 获取体检总结表数据
     * @param batchNo
     * @return
     */
    public List<Batches> showSumData(String batchNo);

    /**
     * 获取人员信息
     * @param cardNos   卡片编号
     * @return
     */
    public List<Cards> showPersonData(String cardNos);




}
