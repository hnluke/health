import com.dao.*;
import com.model.pojo.*;
import com.mysql.jdbc.UpdatableResultSet;
import com.service.IChargeService;
import com.service.ICheckStationService;
import com.service.IManageService;
import com.service.IUsersService;
import com.service.impl.UsersServiceImpl;
import com.util.ExcelPlug;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:spring-mybatis.xml"})
public class TestSpring {
    @Resource
    private UnionQueryMapper unionQueryMapper;
    @Autowired
    private BatchesMapper batchesMapper;
    @Resource
    private Batches batches;
    @Resource
    private AssociationMapper associationMapper;
    @Resource
    private ItemMapper itemMapper;
    @Resource
    private ICheckStationService checkStationService;
    @Autowired
    private SummaryMapper summaryMapper;
    @Resource
    private Summary summary;
    @Resource
    private Association association;
    @Autowired
    private SelectsMapper selectsMapper;
    @Autowired
    private CardsMapper cardsMapper;
    @Resource
    private IManageService manageService;
    @Resource
    private ExcelPlug excelPlug;
    @Resource
    private MenuPrioMapper menuPrioMapper;
    @Resource
    private MenuPrio menuPrio;
    @Resource
    private IChargeService chargeService;
    @Resource
    private Person person;
    @Resource
    private Cards cards;
    @Resource
    private Apps apps;
    @Resource
    private IUsersService usersService;
    @Resource
    private UsersDaoMapper usersDaoMapper;

    @Test
    public void testCode() {
//        List<Cards> cards = unionQueryMapper.queryCardsPerson("1001");
//        System.out.println(cards.get(0).getPerson());
//        System.out.println(cards.get(0).getPerson().getPerId());
//        boolean flag = batchesMapper.insertBatches(batches);
//        System.out.println(flag);
//        System.out.println(getNumberForBatchNo());
//        batches.setPerId(cards.get(0).getPerson().getPerId());
//        batches.setBatchNo(getNumberForBatchNo());
//        batches.setBatchPay("未缴费");
//        batches.setBatchCmp("未完成");
//        batchesMapper.insertBatches(batches);
//        associationMapper.findAssociation("");
//        itemMapper.findItem("");
//        List<String> list = new ArrayList<String>();
//        list.add("a");
//        checkStationService.createList("1001", list);
//        List<Briefs> listBrief = unionQueryMapper.queryGuideCheckData(0, "", "");
//        System.out.println(listBrief);
//        List<Batches> listBatches = unionQueryMapper.queryGuideCheckList(
//                "","","","",""
//        );
//        System.out.println(listBatches);
//        List<Briefs> listBrief = unionQueryMapper.queryCheckReport(15);
//        System.out.println(listBrief);
//        summary.setBatchId(4);
//        if(summaryMapper.insertSummary(summary)) {
//            System.out.println("插入总结表成功");
//        }else {
//            System.out.println("插入总结表失败");
//        }

//        List<Association> listAsso = associationMapper.findAssociation("");
//        System.out.println(listAsso);
//        List<Selects> listSelect = checkStationService.insertSelect("abc", "");
//        System.out.println(listSelect);
//        List<String> listStr = selectsMapper.findSelectNames();
//        System.out.println(listStr);
//        List<String> list = new ArrayList<String>();
//        list.add("abc");
//        list.add("aby");
//        list.add("acu");
//        list.add("acu");
//        ((ArrayList)list).remove("ac");
//        list.clear();
//        System.out.println(list);
//       long distinctSize = list.stream().distinct().count();
//       System.out.println(distinctSize < list.size());
//        Batches batches = new Batches();
//        Cards cards = new Cards();
//        cards.setCardNo("");
//        batches.setBatchPay("已缴费");
//        batches.setBatchCmp("已完成");
//        List<Batches> listBatch = unionQueryMapper.queryGuideCheckList(batches,
//                new Person(), cards);
//        List<Batches> listBatch = checkStationService.showGuideGheck(batches, new Person(), cards);
//        System.out.println(listBatch);
//        List<Briefs> listBrief = unionQueryMapper.queryBriesfLists(17);
//        System.out.println(listBrief);

//        List<Briefs> listBrief = unionQueryMapper.queryBriesfDetails("");
//        System.out.println(listBrief);

//        List<Briefs> listBrief = checkStationService.showReportData("20200429205303");
//        System.out.println(listBrief.get(0).getListDetails());

//        List<Briefs> listBrief = checkStationService.showReportData("20200429205303");
//        System.out.println(listBrief);

//        List<Batches> sumData = checkStationService.showSumData("20200501093437");
//        System.out.println(sumData);

//        List<Cards> cardsList = unionQueryMapper.queryCardsPerson("");
//        System.out.println(cardsList);
//        String str = String.format("%08d", 1);
//        System.out.println(str);
//        List<Item> itemList = unionQueryMapper.queryBriefData("","a");
//        System.out.println(itemList.get(0).getSubItemList());
//        int i = Integer.parseInt("0000001");
//        System.out.println(i);
//        Cards cards = cardsMapper.findLastCard();
//        System.out.println(cards);
//        System.out.print(unionQueryMapper.queryCardsPerson("00000001"));
//        String basePath = TestSpring.class.getClassLoader().getResource("common/menu.xls").getPath();
        //String basePath = TestSpring.class.getClassLoader().getResource("//").getHost();
//        excelPlugMenuPrio.importPrioExcelToDB(basePath);
        //System.out.println(priorityList);

        //List<Menus> list = new ArrayList<Menus>();
//        list = excelPlug.getAllByExcel(basePath);
//        System.out.println(list);
        //System.out.println(basePath);
//        System.out.println(unionQueryMapper.queryPrioMenu("一般用户"));
//        menuPrio.setMenuId(1);
//        menuPrio.setPrioId(1);
//        menuPrioMapper.insertMenuPrio(1, 1);
//        System.out.println(unionQueryMapper.queryPrioMenu("").get(2).getMenuPrioList().get(0).getMenus());
//        List<Priority> prioMenuList = null;
//        manageService.relatePrioMenu(1, 3);
//        manageService.relatePrioMenu(1, 4);
//        manageService.relatePrioMenu(1, 5);
//        prioMenuList = manageService.queryPrioMenu("");
//         System.out.println(prioMenuList);
//        System.out.println(unionQueryMapper.queryBriefData("",""));
//        String basePath = TestSpring.class.getClassLoader().getResource("common/resc.xls").getPath();
//        excelPlug.importOfficeExcelToDB(basePath);
        //excelPlug.importAssoExcelToDB(basePath);
        //excelPlug.importItemExcelToDB(basePath);
//        excelPlug.importSubItemExcelToDB(basePath);
//        System.out.println(unionQueryMapper.queryBriefData("", "过敏原全套"));
//        System.out.println(unionQueryMapper.queryAssoItem(""));
//        List<Cards> cardsList = unionQueryMapper.queryCardsPerson("00000001");
//        System.out.println(cardsList.size() < 1);
//        List<Cards> stringList = unionQueryMapper.queryCardsNotPerson();
//        System.out.println(stringList);
        //System.out.println(chargeService.queryCardsPerson(""));
//        List<Briefs> briefsList = unionQueryMapper.queryBriesfLists(0);
//        List<Briefs> briefsList = unionQueryMapper.queryBriesfDetails("20200510083559");
//        for(Briefs briefs : briefsList) {
//            List<Details> detailsList = briefs.getListDetails();
//            for(Details details : detailsList) {
//                System.out.println(details.getDetItemName() + ":" + details.getDetLower() + ":" + details.getDetUpper());
//            }
//        }
//        System.out.println(briefsList);
//        briefsList.get(0).getBriefBatchNo();
//        System.out.println(briefsList);
//        List<Batches> batchesList = null;
//        batches.setBatchPay("未缴费");
//        batches.setBatchCmp("未完成");
//        cards.setCardNo(apps.getCardNo());
//        batchesList = unionQueryMapper.queryGuideCheckList(batches, person, cards);
//        System.out.println(batchesList);

//        System.out.println(unionQueryMapper.queryPayBalance(new Batches(), new Cards()));
//        List<Menus> menusList = unionQueryMapper.queryMenesPrioUsers("lisa");
//        System.out.println(menusList);
//        List<Menus> menusList = null;
//        menusList = usersService.fetchUserMenus("luke");
//        System.out.println(menusList);

//        System.out.println(usersDaoMapper.findUsersByName(null));
//        System.out.println(unionQueryMapper.queryUsersOffPrio(0));

        System.out.println(usersService.changePwd("222",7));
    }

    public String getNumberForBatchNo() {
        String id="";
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String temp = sf.format(new Date());
        //int random=(int) (Math.random()*100);
        //id=temp+random;
        id = temp;
        return id;
    }



/**
 * 通过简单的代码判断List中是否包含相同元素
 * @author wei 2017年7月10日 下午8:34:47
 */
    public void listHaveRepeat() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("2");
        // 通过去重之后的HashSet长度来判断原list是否包含重复元素
        boolean isRepeat = list.size() != new HashSet<String>(list).size();
        System.out.println("list中包含重复元素：" + isRepeat);
    }


}
