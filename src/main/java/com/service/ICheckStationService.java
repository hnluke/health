package com.service;

import com.model.pojo.Association;
import com.model.pojo.Batches;
import com.model.pojo.Item;
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
    public boolean createList(String cardNos, List<String> itemNames);

    /**
     * 打印导检表
     * @author Luke
     * @param batchId 流水表id
     */
    public void printGuideCheck(Integer batchId);

    /**
     * 显示导检表打印列表, 体检报告列表
     * @param cardNo        根据卡片编号来显示导检表或检查报告列表
     * @param prt           是否已经打印导检表
     * @param prtRpts       是否已经打印检查报告
     * @param batchPays     是否已经缴费
     * @param batchCmps     是否已经完成
     * @return
     */
    public List<Batches> showGuideGheck(String cardNo, String prt,
                                        String prtRpts, String batchPays,
                                        String batchCmps);

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

}
