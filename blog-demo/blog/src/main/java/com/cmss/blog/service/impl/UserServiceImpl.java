package com.cmss.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cmss.blog.dao.mapper.UserMapper;
import com.cmss.blog.dao.pojo.User;
import com.cmss.blog.service.UserService;
import com.cmss.blog.utils.JWTUtil;
import com.cmss.blog.vo.CommenResult;
import com.cmss.blog.vo.ErrorCode;
import com.cmss.blog.vo.UserVo;
import com.cmss.blog.vo.params.LoginParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public CommenResult register(LoginParam loginParam) {

//        参数校验 查询是否存在 存入数据库
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            System.out.println("参数为空");
            return CommenResult.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        //查询数据库是否存在此用户
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount , account);
        lambdaQueryWrapper.eq(User::getPassword , password);

        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (user != null){
            System.out.println("用户已经存在");
            return CommenResult.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }

        user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setNickname(loginParam.getNickname());
        userMapper.insert(user);

        String token = JWTUtil.createToken(user.getId() , user.getAccount() , user.getNickname());
        return CommenResult.success(token);
    }

    @Override
    public CommenResult login(LoginParam loginParam) {

        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            System.out.println("参数为空");
            return CommenResult.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        //查询数据库是否存在此用户
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount , account);
        lambdaQueryWrapper.eq(User::getPassword , password);

        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (user == null){
            System.out.println("用户不存在");
            return CommenResult.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        String token = JWTUtil.createToken(user.getId() , user.getAccount() , user.getNickname());

        return CommenResult.success(token);
    }

    @Override
    public CommenResult loginOut(LoginParam loginParam) {

        String account = loginParam.getAccount();
        if (StringUtils.isBlank(account)) {
            System.out.println("参数为空");
            return CommenResult.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }

        return CommenResult.success("loginOut，删除token");
    }

    @Override
    public CommenResult currentUserInfo(String token) {

        Map<String, Object> stringObjectMap = JWTUtil.checkToken(token);
        Object userId = stringObjectMap.get("userId");
        Object account = stringObjectMap.get("account");
        Object nickname = stringObjectMap.get("nickname");

        UserVo userVo = new UserVo();
        userVo.setId(Long.parseLong(String.valueOf(userId)));
        userVo.setAccount(String.valueOf(account));
        userVo.setNickname(String.valueOf(nickname));
        return CommenResult.success(userVo);
    }

}
