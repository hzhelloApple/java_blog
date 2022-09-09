package com.cmss.blog.service;

import com.cmss.blog.vo.CommenResult;
import com.cmss.blog.vo.params.LoginParam;

public interface UserService {

    CommenResult register(LoginParam loginParam);

    CommenResult login(LoginParam loginParam);

    CommenResult loginOut(LoginParam loginParam);
}
