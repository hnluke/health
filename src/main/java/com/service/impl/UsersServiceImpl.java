package com.service.impl;

import com.dao.UsersDaoMapper;
import com.model.pojo.Users;
import com.service.IUsersService;
import com.util.MD5App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean verify(String userName, String userPwd) {
        boolean flag = false;
        List<Users> users = usersDaoMapper.findUsersByName(userName);
        Users user = users.get(0);
        if (userPwd.equals(user.getUserPwd())){
            flag=true;
        }

//        if(users != null) {
//            try {
//                if(md5App.validPassword(userPwd, users.getUserPwd())) {
//                    flag = true;
//                }
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }

        return flag;
    }

    @Override
    public List<Users> findAll(String userName) {
       List<Users> list= usersDaoMapper.findUsersByName(userName);

        return list;
    }


    //更新密码
    @Override
    public int changePwd(String userPwd, String newPwd,Integer userId) {
        int rows = usersDaoMapper.changePwd(newPwd,userId);

        return rows;
    }


}
