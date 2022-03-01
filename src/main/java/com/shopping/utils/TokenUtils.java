package com.shopping.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.shopping.entity.authentication.UserAccount;
import com.shopping.entity.authentication.UserInfo;
import com.shopping.mapper.authentication.AccountRoleMapper;
import com.shopping.mapper.authentication.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class TokenUtils {
    @Autowired
    private AccountRoleMapper accountRoleMapper;

    private static AccountRoleMapper staticUserMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    private static UserInfoMapper staticInfoMapper;

    @Autowired
    private RedisCache redisCache;

    private static RedisCache cache;

    @PostConstruct
    public void init() {
        staticUserMapper = accountRoleMapper;
        staticInfoMapper = userInfoMapper;
        cache = redisCache;
    }

    public static UserInfo getToken(UserAccount userAccount) {
        UserInfo userInfo = staticInfoMapper.queryLoginUserInfoById(userAccount.getId());
        cache.setCacheObject(userInfo.getId()+"",userInfo);
        String token = JWT.create().withExpiresAt(DateUtil.offsetDay(new Date(), 1)).withAudience(""+userAccount.getId())
                .sign(Algorithm.HMAC256(userAccount.getAccount()));
        userInfo.setToken(token);
        return userInfo;
    }

    public static UserInfo getLoginUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            String aud = JWT.decode(token).getAudience().get(0);
            UserInfo userInfo = cache.getCacheObject(aud+"");
            if(Objects.isNull(userInfo)){
                return null;
            }
            return userInfo;
        } catch (Exception e) {
            log.error("解析token失败", e);
            return null;
        }
    }

}
