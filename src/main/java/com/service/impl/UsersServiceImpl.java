package com.service.impl;

import com.dao.UsersDaoMapper;
import com.model.pojo.Users;
import com.service.IUsersService;
//import com.util.MD5App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class UsersServiceImpl implements IUsersService{
//    @Autowired
//    private UsersDaoMapper usersDaoMapper;
////    @Autowired
////    private MD5App md5App;
//
//    @Override
    public boolean verify(String userName, String userPwd) {
        boolean flag = false;
////        Users users = usersDaoMapper.findUsersByName(userName);
////        if(users != null) {
////            try {
////                if(md5App.validPassword(userPwd, users.getUserPwd())) {
////                    flag = true;
////                }
////            } catch (NoSuchAlgorithmException e) {
////                e.printStackTrace();
////            } catch (UnsupportedEncodingException e) {
////                e.printStackTrace();
////            }
////        }
//
        return flag;
    }
}
