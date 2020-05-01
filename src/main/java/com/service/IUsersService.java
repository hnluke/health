package com.service;

public interface IUsersService {
    /**
     * 验证用户是否合法
     * @param magUserName   用户名
     * @param magUserPwd    密码
     * @return
     */
    public boolean verify(String magUserName, String magUserPwd);
}
