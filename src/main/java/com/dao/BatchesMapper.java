package com.dao;

import com.model.pojo.Batches;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BatchesMapper {
    /**
     * 新增流水记录
     * @param batches 流水表对象
     * @return
     */
    public boolean insertBatches(Batches batches);

    /**
     * 更新流水表记录
     * @param batches 流水表对象
     * @return
     */
    public boolean updateBatches(Batches batches);

    /**
     * 查询流水表
     * @param id  依据流水表id查询，如果id=0，则查询所有的流水记录
     * @return 流水表对象
     */
    public List<Batches> findBatches(Integer id);

    public boolean updateBatchesPay(@Param("batchId") Integer batchId, @Param("batchPay") String batchPay);
}
