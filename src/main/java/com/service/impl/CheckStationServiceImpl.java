package com.service.impl;

import com.dao.*;
import com.model.pojo.*;
import com.service.ICheckStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CheckStationServiceImpl  implements ICheckStationService {
    @Autowired
    private BatchesMapper batchesMapper;
    @Autowired
    private UnionQueryMapper unionQueryMapper;
    @Autowired
    private BriefsMapper briefsMapper;
    @Resource
    private Batches batches;
    @Autowired
    private ListsMapper listsMapper;
    @Autowired
    private SummaryMapper summaryMapper;
    @Autowired
    private AssociationMapper associationMapper;
    @Resource
    private Lists lists;
    @Resource
    private Item item;
    @Autowired
    private ItemMapper itemMapper;
    @Resource
    private Briefs briefs;
    @Resource
    private Summary summary;
    @Resource
    private Association association;


    /**
     * 开单
     * @author Luke
     * @param cardNos 卡片编号
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public boolean createList(String cardNos, List<String> itemNames) {
        boolean flag = false;
        Double listPrice = 0D;      // 价格
        String listType = "";       // 项目类型
        String batchNo = "";        // 流水编号
        String person = "";         // 体检人
        Integer listId;             // 开单表id

        // 根据卡片编号查找到体检人id
        List<Cards> cards = unionQueryMapper.queryCardsPerson(cardNos);
        batches.setPerId(cards.get(0).getPerson().getPerId());
        batchNo = getNumberForBatchNo();
        person = cards.get(0).getPerson().getPerName();
        batches.setBatchNo(batchNo);
        batches.setBatchPay("未缴费");
        batches.setBatchCmp("未完成");
        batches.setBatchPrt("未打印");
        batches.setBatchPrtRpt("未打印");
        // 创建流水表记录
        batchesMapper.insertBatches(batches);
        // 获取自动回写的流水表主键值。
        Integer batchId = batches.getBatchId();
        summary.setBatchId(batchId);
        summaryMapper.insertSummary(summary);
        lists.setBatchId(batchId);
        for(String itemName : itemNames) {
            List<Association> listAsso = associationMapper.findAssociation(itemName);
            if(listAsso.size() > 0) {
                listType = "套餐";
                listPrice = listAsso.get(0).getAssoPrice();      // 得到套餐价格
            } else {
                List<Item> listItem = itemMapper.findItem(itemName);
                if(listItem.size() > 0) {
                    listType = "项目";
                    listPrice = listItem.get(0).getItemPrice();
                }
            }
            lists.setBatchId(batchId);
            lists.setListName(itemName);
            lists.setListType(listType);
            lists.setListPrice(listPrice);
            // 向开单表添加记录
            listsMapper.insertLists(lists);
            // 得到添加记录的id值
            listId = lists.getListId();
            List<Item> listItems;
            if("套餐".equals(listType)) {
                listItems = unionQueryMapper.queryBriefData(itemName, "");

            }else{
                listItems = unionQueryMapper.queryBriefData("", itemName);
            }
            // 向小结表插入项目记录，以备小结用
            for(Item listItem : listItems) {
                // 将开单表id值写入小结表关联id形成关联
                briefs.setListId(listId);               // 关联表id
                briefs.setBriefItemName(itemName);          //项目名称
                briefs.setBriefType(listItem.getItemType().getTypeName());  //项目类别
                briefs.setBriefName(listItem.getOffice().getOffName());     // 科室
                briefs.setBriefComp("未完成");             // 是否已完成
                briefs.setBriefPay("未缴费");              // 是否已缴费
                briefs.setBriefBatchNo(batchNo);          // 流水号
                briefs.setBriefCardNo(cardNos);           // 卡片编号
                briefs.setBriefPerson(person);            // 体检人
                briefsMapper.insertBriefs(briefs);        // 向小结表插入一条项目记录
            }
        }


        return flag;
    }

    /**
     * 打印导检表
     * @author Luke
     * @param batchId   流水表id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public void printGuideCheck(Integer batchId) {

    }

    @Override
    public List<Batches> showGuideGheck(String cardNo, String prt,
                                        String prtRpts, String batchPays,String batchCmps) {
        List<Batches> batchList = new ArrayList<Batches>();
        batchList = unionQueryMapper.queryGuideCheckList(cardNo, prt, prtRpts, batchPays, batchCmps);
        return batchList;
    }

    /**
     * 产生流水号
     * @author Luke
     * @return
     */
    public String getNumberForBatchNo(){
        String id="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String temp = sdf.format(new Date());
        //int random=(int) (Math.random()*100);
        //id=temp+random;
        id = temp;
        return id;
    }

    /**
     * @author Luke
     * @param assoNames  套餐名 如果为null或空，则查询套餐表所有的数据
     * @return
     */
    @Override
    public List<Association> showAllAsso(String assoNames) {
        List<Association> listAsso;
        listAsso = associationMapper.findAssociation(assoNames);
        return listAsso;
    }


    /**
     * 查询项目表
     * @author Luke
     * @param itemName  项目名称，如果为null或空，则查询项目表中所有的数据
     * @return
     */
    public List<Item> showAllItem(String itemName) {
        List<Item> listItem;
        listItem = unionQueryMapper.queryBriefData("", "");
        return listItem;
    }
}
