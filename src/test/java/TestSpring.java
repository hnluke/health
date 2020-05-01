import com.dao.*;
import com.model.pojo.*;
import com.service.ICheckStationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.annotation.Resource;
import java.awt.image.renderable.RenderableImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
//        List<Briefs> listBrief = unionQueryMapper.queryGuideCheckData(0);
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

        List<Association> listAsso = associationMapper.findAssociation("");
        System.out.println(listAsso);


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
