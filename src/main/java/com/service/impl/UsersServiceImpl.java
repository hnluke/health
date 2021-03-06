package com.service.impl;

import com.dao.UnionQueryMapper;
import com.dao.UsersDaoMapper;
import com.model.pojo.Menus;
import com.model.pojo.Users;
import com.service.IUsersService;
import com.util.MD5App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements IUsersService{
    @Autowired
    private UsersDaoMapper usersDaoMapper;
    @Autowired
    private MD5App md5App;
    @Resource
    private UnionQueryMapper unionQueryMapper;

    @Override
    public boolean verify(String userName, String userPwd) {
        boolean flag = false;
//        if(userName == null || "".equals(userName.trim())) {
//            return false;
//        }
        List<Users> users = usersDaoMapper.findUsersByName(userName);
        if("".equals(userName) && users.size() < 1) {
            return true;
        }else{
            if(users.size() < 1) {
                return false;
            }else {
                Users user = users.get(0);
                if (userPwd.equals(user.getUserPwd())){
                    flag = true;
                }
            }

        }
        return flag;
    }

    @Override
    public List<Users> findAll(String userName) {
       List<Users> list= usersDaoMapper.findUsersByName(userName);

        return list;
    }


    //更新密码
    @Override
    public int changePwd(String newPwd,Integer userId) {
        int rows = usersDaoMapper.changePwd(newPwd,userId);
        return rows;
    }

    /**
     * 获取用户权限的菜单数据
     * @author Luke
     * @param userName
     * @return
     */
    public List<Menus> fetchUserMenus(String userName) {
        List<Menus> menusList = unionQueryMapper.queryMenesPrioUsers(userName);
        return menusList;
    }

    public boolean insertUsers(Users user) {
        return usersDaoMapper.insertUsers(user);
    }

    public boolean deleteUsers(Integer userId) {
        return usersDaoMapper.deleteUsersById(userId);
    }


}
