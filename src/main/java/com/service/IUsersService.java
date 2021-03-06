package com.service;

import com.model.pojo.Menus;
import com.model.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUsersService {
    /**
     * 验证用户是否合法
     * @param magUserName   用户名
     * @param magUserPwd    密码
     * @return
     */
     boolean verify(String magUserName, String magUserPwd);


    /**
     * 查找所有用户
     * @param userName
     * @return
     */
     List<Users> findAll(String userName);

    /**
     * 修改用户密码
     * @param newPwd
     * @param userId
     * @return
     */
    int changePwd( String newPwd,Integer userId);

    /**
     * 得到用户的菜单集合
     * @param userName
     * @return
     */
    public List<Menus> fetchUserMenus(String userName);

    /**
     * 新增用户
     * @param user
     * @return
     */
    public boolean insertUsers(Users user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public boolean deleteUsers(Integer userId);


}
