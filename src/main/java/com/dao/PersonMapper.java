package com.dao;

import com.model.pojo.Person;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PersonMapper {

    /**
     * 查询体检人
     * @param perName  体检人姓名
     * @return
     */
    public List<Person> findPerson(@Param("perName") String perName);

    /**
     * 新增体检人
     * @param person
     * @return
     */
    public boolean insertPerson(Person person);

    /**
     * 删除体检人
     * @param perId
     * @return
     */
    public boolean deletePerson(@Param("perId") Integer perId);

    /**
     * 更新体检人信息
     * @param person
     * @return
     */
    public boolean updatePerson(Person person);
}
