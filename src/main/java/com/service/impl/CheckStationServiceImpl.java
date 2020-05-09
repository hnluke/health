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
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private SelectsMapper selectsMapper;
    @Autowired
    private CardsMapper cardsMapper;
    @Resource
    private Details details;
    @Autowired
    private DetailMapper detailMapper;
//    @Resource
//    private Selects selects;


    /**
     * 开单
     * @author Luke
     * @param cardNos 卡片编号
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public String createList(String cardNos, List<String> itemNames) {
        String message = "";
        Double listPrice = 0D;      // 价格
        String listType = "";       // 项目类型
        String batchNo = "";        // 流水编号
        String person = "";         // 体检人
        Integer listId;             // 开单表id

        // 根据卡片编号查找到体检人id
        List<Cards> listCards = null;
        listCards = cardsMapper.findCards(cardNos);
        // 如果无此卡号，则直接返回
        if(listCards == null || listCards.size() < 1) {
            selectsMapper.clearSelect();
            return "未知卡号";
        }
        // 获取选择的所有项目名称列表
        List<String> selectNames = selectsMapper.findSelectNames();
        // 如果名称列表有重复，则返回
        if(selectNames.stream().distinct().count() < selectNames.size()) {
            selectsMapper.clearSelect();
            return "选择项目重复，请重新选择";
        }
        // 依据卡片编号查询到对应的体检人表id
        List<Cards> cards = unionQueryMapper.queryCardsPerson(cardNos);
        // 将体检人表id写入流水表的per_id字段，建立体检表和流水表关联
        batches.setPerId(cards.get(0).getPerson().getPerId());
        batchNo = getNumberForBatchNo();
        person = cards.get(0).getPerson().getPerName();
        batches.setBatchNo(batchNo);
        batches.setBatchPay("未缴费");
        batches.setBatchCmp("未完成");
        batches.setBatchPrt("未打印");
        batches.setBatchPrtRpt("未打印");
        batches.setBatchSum("未打印");
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
            // 创建开单表添加记录
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
                Integer briefId = briefs.getBriefId();
                List<SubItem> subList = listItem.getSubItemList();
                for(SubItem subItem : subList) {
                    details.setBriefId(briefId);
                    details.setDetItemName(subItem.getSubName());
                    details.setDetUnit(subItem.getSubUnit());
                    details.setDetRefer(subItem.getSubRefer());
                    details.setDetUpper(String.valueOf(subItem.getSubUpper()));
                    details.setDetLower(String.valueOf(subItem.getSubLower()));
                    detailMapper.insertDetail(details);
                }
            }
        }
        selectsMapper.clearSelect();
        message = "开单成功";
        return message;
    }


    /**
     * 显示导检列表, 综合查询列表
     * @author luke
     * @param batches   流水表pojo
     * @param person    体检人表pojo
     * @param cards     卡片表pojo
     * @return
     */
    @Override
    public List<Batches> showGuideGheck(Batches batches, Person person, Cards cards) {
        List<Batches> batchList = new ArrayList<Batches>();
        batchList = unionQueryMapper.queryGuideCheckList(batches, person, cards);
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


    /**
     * 将用户选择的项目加入到selects表中
     * @author Luke
     * @param assoName      套餐名
     * @param itemName      项目名
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public List<Selects> insertSelect(String assoName, String itemName) {
        List<Selects> listSelect = null;
        List<Item> listItem = null;
        listItem = unionQueryMapper.queryBriefData(assoName, itemName);
        Selects selects = null;
        if (listItem != null) {
            for(Item item : listItem) {
                selects = new Selects();
                if(assoName != null) {
                    selects.setSelAssoId(item.getAssoItems().get(0).getAssoId());
                    //selects.setSelAssoName(item);
                }else if(itemName != null) {
                    selects.setSelAssoId(0);
                    //selects.setSelAssoName(null);
                }
                selects.setSelItemId(item.getItemId());
                selects.setSelItemName(item.getItemName());
                selects.setSelType(item.getItemType().getTypeName());
                selects.setSelOff(item.getOffice().getOffName());
                selects.setSelPrice(item.getItemPrice());
                selectsMapper.insertSelect(selects);
            }
        }
        listSelect = selectsMapper.findSelect(0);
        return listSelect;
    }

    /**
     * 删除选择记录
     * @author Luke
     * @param assoId
     * @param itemId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public List<Selects> deleteSelect(Integer assoId, Integer itemId) {
        List<Selects> listSelect = null;
        if(selectsMapper.deleteSelect(assoId, itemId)) {
            listSelect = selectsMapper.findSelect(0);
        }
        return listSelect;
    }

    @Override
    public List<Selects> clearSelect() {
        List<Selects> listSelect = null;
        if(selectsMapper.clearSelect()) {
            listSelect = selectsMapper.findSelect(0);
        }
        return listSelect;
    }

    /**
     * 获取导检表数据
     * @author Luke
     * @param batchId   流水表id
     * @return
     */
    @Override
    public List<Briefs> showGuideData(Integer batchId) {
        List<Briefs> briefList = null;
        briefList = unionQueryMapper.queryBriesfLists(batchId);

        return briefList;
    }

    /**
     * 获取体检报告数据
     * @author Luke
     * @param batchNo   流水号
     * @return
     */
    @Override
    public List<Briefs> showReportData(String batchNo) {
        List<Briefs> briefList = null;
        briefList = unionQueryMapper.queryBriesfDetails(batchNo);
        return briefList;
    }

    /**
     * 显示体检总结报告
     * @author Luke
     * @param batchNo
     * @return
     */

    public List<Batches> showSumData(String batchNo) {
        List<Batches> briefList = null;
        briefList = unionQueryMapper.queryBatchSumPerCard(batchNo);
        return briefList;
    }

    /**
     * 查询卡片的人员信息
     * @author Luke
     * @param cardNos   卡片编号
     * @return
     */
    public List<Cards> showPersonData(String cardNos) {
        List<Cards> cardsList = unionQueryMapper.queryCardsPerson(cardNos);
        return cardsList;
    }
}
