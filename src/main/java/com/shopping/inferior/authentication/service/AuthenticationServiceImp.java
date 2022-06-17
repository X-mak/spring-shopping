package com.shopping.inferior.authentication.service;

import com.shopping.entity.authentication.*;
import com.shopping.entity.data.UserEx;
import com.shopping.mapper.authentication.*;
import com.shopping.mapper.data.UserExMapper;
import com.shopping.utils.Encode;
import com.shopping.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AuthenticationServiceImp implements AuthenticationService{


    public int addUser(RegisterUser registerUser){
        try{
            //验证是否存在重复账号
            Example accountExample = new Example(UserAccount.class);
            accountExample.createCriteria().andEqualTo("account",registerUser.getUserAccount());
            List<UserAccount> userAccounts = userAccountMapper.selectByExample(accountExample);
            if(userAccounts.size()>0){
                return 0;
            }

            //加密密码
            String randomNum = (int)((Math.random()*9+1)*100)+"";
            String encodePwd = encode.getSHA256StrJava(randomNum) + encode.getSHA256StrJava(registerUser.getPwd());

            //初始化及数据库操作
            UserAccount userAccount = new UserAccount(registerUser.getUserAccount(),encodePwd);
            userAccountMapper.insertSelective(userAccount);
            int userId = userAccount.getId();
            UserInfo userInfo = new UserInfo(userId,registerUser.getUserName(), registerUser.getUserPhone());
            userInfo.setUserStatus(1);
            accountRoleMapper.insertSelective(new AccountRole(1,userId));
            if(registerUser.getUserRole().equals("seller")){
                userInfo.setUserStatus(1);
                accountRoleMapper.insertSelective(new AccountRole(2,userId));
            }else if(registerUser.getUserRole().equals("admin")){
                accountRoleMapper.insertSelective(new AccountRole(2,userId));
                accountRoleMapper.insertSelective(new AccountRole(3,userId));
            }
            userInfoMapper.insertSelective(userInfo);
            if(registerUser.getAddress()!=null && !registerUser.getAddress().equals("")){
                userExMapper.insertSelective(new UserEx(userId,1,registerUser.getAddress(),1));
            }

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public UserInfo checkLogin(UserAccount userAccount){

        Example example = new Example(UserAccount.class);
        example.createCriteria().andEqualTo("account",userAccount.getAccount());
        UserAccount selectedAccount = new UserAccount();
        try{
            selectedAccount = userAccountMapper.selectByExample(example).get(0);
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(selectedAccount.getId());
            if(userInfo.getUserStatus() != 1 )return null;
        }catch (Exception e){
            return null;
        }

        String encodePwd = encode.getSHA256StrJava(userAccount.getPwd());
        if(selectedAccount == null || ! encodePwd.equals(selectedAccount.getPwd().substring(64)))
            return null;
        return TokenUtils.getToken(selectedAccount);
    }

    public int changePwd(UserAccount userAccount,String newPwd){
        try{
            //验证账号密码是否正确
            Example example = new Example(UserAccount.class);
            example.createCriteria().andEqualTo("account",userAccount.getAccount());
            UserAccount selectedAccount = userAccountMapper.selectByExample(example).get(0);
            String encodePwd = encode.getSHA256StrJava(userAccount.getPwd());
            if(selectedAccount == null || ! encodePwd.equals(selectedAccount.getPwd().substring(64)))
                return 0;

            //修改密码
            String randomNum = (int)((Math.random()*9+1)*100)+"";
            String newEncodePwd = encode.getSHA256StrJava(randomNum)+encode.getSHA256StrJava(newPwd);
            selectedAccount.setPwd(newEncodePwd);
            userAccountMapper.updateByPrimaryKeySelective(selectedAccount);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Autowired
    AccountRoleMapper accountRoleMapper;
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    Encode encode;
    @Autowired
    UserExMapper userExMapper;
}
