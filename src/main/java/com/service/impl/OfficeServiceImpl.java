package com.service.impl;

import com.dao.OfficeDaoMapper;
import com.dao.UsersDaoMapper;
import com.model.pojo.Office;
import com.model.pojo.Users;
import com.service.IOfficeService;
import com.service.IUsersService;
import com.util.MD5App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements IOfficeService {
    @Autowired
    private OfficeDaoMapper officeDaoMapper;


    /**
     * 查找所有科室
     * @return
     */
    @Override
    public List<Office> findAll() {
       List<Office> list= officeDaoMapper.findOfficesById(null);
        return list;
    }

    /**
     * 删除科室
     * @param offId
     * @return
     */
    @Override
    public boolean deleteOffice(Integer offId) {
        boolean flag = officeDaoMapper.deleteOffice(offId);
        return flag;
    }

    /**
     * 增加科室
     * @param offName
     * @return
     */
    @Override
    public boolean insertOffice(String offName) {
        boolean flag = officeDaoMapper.insertOffice(offName);
        return flag;
    }


}
