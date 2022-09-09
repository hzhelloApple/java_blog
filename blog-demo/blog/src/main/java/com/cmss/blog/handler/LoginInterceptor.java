package com.cmss.blog.handler;

import com.alibaba.fastjson.JSON;
import com.cmss.blog.vo.CommenResult;
import com.cmss.blog.vo.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        拦截请求：
//           1、放行静态资源
//           2、拦截查看是否有token参数
        if (!(handler instanceof HandlerMethod)){
//            放行静态资源
            return true;
        }

        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)){
//            没有token，未登录
            CommenResult result = CommenResult.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

//        检查token在相应的接口中实现
        return true;
    }
}
